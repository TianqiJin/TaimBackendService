package com.taim.taimbackendservice.manager.transaction;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.taim.taimbackendservice.exception.ResourceNotFoundException;
import com.taim.taimbackendservice.helper.TransactionCalculationHelper;
import com.taim.taimbackendservice.mapper.QuotationDTOMapper;
import com.taim.taimbackendservice.model.*;
import com.taim.taimbackendservice.model.enums.TaxType;
import com.taim.taimbackendservice.model.enums.TransactionStatus;
import com.taim.taimbackendservice.model.enums.TransactionType;
import com.taim.taimbackendservice.repository.CustomerRepository;
import com.taim.taimbackendservice.repository.ProductRepository;
import com.taim.taimbackendservice.repository.QuotationRepository;
import com.taim.taimbackendservice.repository.StaffRepository;
import com.taim.taimbackendservicemodel.CreateQuotationDTO;
import com.taim.taimbackendservicemodel.QuotationDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.taim.taimbackendservice.utils.Constants.ALLOWED_GST_RATES;
import static com.taim.taimbackendservice.utils.Constants.ALLOWED_PST_RATES;

@Service
@Slf4j
public class TransactionManagerImpl implements TransactionManager {

    private final Gson gson = new Gson();
    private final QuotationRepository quotationRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final StaffRepository staffRepository;
    private final TransactionCalculationHelper transactionCalculationHelper;
    private final QuotationDTOMapper quotationDTOMapper;

    @Autowired
    public TransactionManagerImpl(QuotationRepository quotationRepository,
                                  CustomerRepository customerRepository,
                                  ProductRepository productRepository,
                                  StaffRepository staffRepository,
                                  TransactionCalculationHelper transactionCalculationHelper,
                                  QuotationDTOMapper quotationDTOMapper) {
        this.quotationRepository = quotationRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.staffRepository = staffRepository;
        this.transactionCalculationHelper = transactionCalculationHelper;
        this.quotationDTOMapper = quotationDTOMapper;
    }

    @Override
    public QuotationDTO getByQuotationId(String quotationId) {
        Optional<Quotation> quotationOptional = Optional.ofNullable(this.quotationRepository.getByQuotationId(quotationId));

        return quotationOptional.map(this.quotationDTOMapper::convert)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find quotation by quotation Id " + quotationId));
    }

    @Override
    public List<QuotationDTO> getAllQuotations() {
        Optional<List<Quotation>> quotations = Optional.ofNullable(this.quotationRepository.findAll());

        return quotations
                .map(quotationList -> quotationList.stream()
                        .map(this.quotationDTOMapper::convert)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public void saveAndSubmitQuotation(CreateQuotationDTO createQuotationDTO) {

        Quotation quotation = this.quotationRepository.getByQuotationId(createQuotationDTO.getQuotationId());

        // Retrieve customer detail
        Customer quotationCustomer = this.customerRepository.getOne(createQuotationDTO.getCustomerId());
        // Retrieve staff detail
        Staff quotationStaff = this.staffRepository.getOne(1L);

        if (quotation == null) {
            quotation = new Quotation();
            log.info("Creating new quotation - {}", gson.toJson(createQuotationDTO));
        } else {
            log.info("Saving and submitting existing quotation - {}", gson.toJson(createQuotationDTO));
        }

        quotation.setCustomer(quotationCustomer);
        quotation.setStaff(quotationStaff);
        quotation.setNote(createQuotationDTO.getNote());
        quotation.setStatus(TransactionStatus.getStatus(createQuotationDTO.getStatus()));
        quotation.setQuotationId(createQuotationDTO.getQuotationId());
        quotation.setTransactionType(TransactionType.QUOTATION);
        quotation.setQuotationDate(createQuotationDTO.getQuotationDate());
        quotation.setDueDate(createQuotationDTO.getDueDate());
        if (createQuotationDTO.isSubmit()) {
            quotation.setStatus(TransactionStatus.SUBMITTED);
        } else {
            quotation.setStatus(TransactionStatus.CREATED_NOT_SUBMITTED);
        }
        quotation.setBillToAddress(createQuotationDTO.getBillToAddress());
        quotation.setBillFromAddress(createQuotationDTO.getBillFromAddress());

        List<TransactionDetail> newTransactionDetailList = createQuotationDTO.getCreateQuotationDetailDTOList()
                .stream()
                .map(createQuotationDetailDTO -> {
                    TransactionDetail transactionDetail = new TransactionDetail();
                    transactionDetail.setQuantity(createQuotationDetailDTO.getQuantity());
                    transactionDetail.setDiscount(createQuotationDetailDTO.getDiscount());
                    transactionDetail.setUnitPrice(createQuotationDetailDTO.getUnitPrice());
                    transactionDetail.setNote(createQuotationDetailDTO.getNote());
                    transactionDetail.setProduct(
                            // Retrieve product details
                            this.productRepository.getProductBySku(createQuotationDetailDTO.getProductSku()));
                    List<TaxInfo> taxInfoList = createQuotationDetailDTO.getCreateQuotationDetailTaxItemDTOList().stream()
                            .map(createQuotationDetailTaxItemDTO -> {
                                TaxInfo taxInfo = new TaxInfo();
                                taxInfo.setTaxType(createQuotationDetailTaxItemDTO.getTaxType());
                                taxInfo.setTaxRate(createQuotationDetailTaxItemDTO.getTaxRate());
                                transactionCalculationHelper.calculateTaxInfo(taxInfo,
                                        transactionDetail.getQuantity(),
                                        transactionDetail.getUnitPrice());

                                return taxInfo;
                            })
                            .collect(Collectors.toList());
                    transactionDetail.setTaxInfos(taxInfoList);
                    transactionCalculationHelper.calculateTransactionDetailTotal(transactionDetail);

                    return transactionDetail;
                }).collect(Collectors.toList());

//        // Update product total virtual number
//        List<Product> updateProductList = new ArrayList<>();
//        if (CollectionUtils.isNotEmpty(existingTransactionDetailList)) {
//            updateProductList = existingTransactionDetailList.stream()
//                    .map(transactionDetail -> {
//                        Product product = transactionDetail.getProduct();
//                        product.setTotalNumVirtual(product.getTotalNumVirtual().add(transactionDetail.getQuantity()));
//
//                        return product;
//                    })
//                    .collect(Collectors.toList());
//        }
//
//        for(TransactionDetail transactionDetail: newTransactionDetailList) {
//            Optional<Product> productOptional = updateProductList.stream()
//                    .filter(existingProduct -> existingProduct.getSku().equals(transactionDetail.getProduct().getSku()))
//                    .findAny();
//            if (productOptional.isPresent()) {
//                Product product = productOptional.get();
//                product.setTotalNumVirtual(product.getTotalNumVirtual().subtract(transactionDetail.getQuantity()));
//            } else {
//                Product product = transactionDetail.getProduct();
//                product.setTotalNumVirtual(product.getTotalNumVirtual().add(transactionDetail.getQuantity()));
//                updateProductList.add(product);
//            }
//        }

        if (CollectionUtils.isNotEmpty(quotation.getTransactionDetails())) {
            quotation.getTransactionDetails().clear();
            quotation.getTransactionDetails().addAll(newTransactionDetailList);
        } else {
            quotation.setTransactionDetails(newTransactionDetailList);
        }

        transactionCalculationHelper.calculateTransactionTotal(quotation);

        this.quotationRepository.save(quotation);
    }


    @Override
    @Transactional
    public Map<TaxType, List<BigDecimal>> getAllowedTaxRateMap() {
        return ImmutableMap.of(
                TaxType.GST, ALLOWED_GST_RATES,
                TaxType.PST, ALLOWED_PST_RATES
        );
    }

    @Override
    @Transactional
    public List<QuotationDTO> getQuotationListByCustomerId(Long customerId) {
       Optional<List<Quotation>> quotations = Optional.ofNullable(
               this.quotationRepository.getQuotationsByCustomerId(customerId));

        return quotations
                .map(quotationList -> quotationList.stream()
                        .map(this.quotationDTOMapper::convert)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
