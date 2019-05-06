package com.taim.taimbackendservice.controller;

import com.taim.taimbackendservice.helper.TransactionCalculationHelper;
import com.taim.taimbackendservice.model.Customer;
import com.taim.taimbackendservice.model.Quotation;
import com.taim.taimbackendservice.model.Staff;
import com.taim.taimbackendservice.model.TransactionDetail;
import com.taim.taimbackendservice.model.enums.TransactionStatus;
import com.taim.taimbackendservice.model.enums.TransactionType;
import com.taim.taimbackendservice.service.customer.ICustomerService;
import com.taim.taimbackendservice.service.product.IProductService;
import com.taim.taimbackendservice.service.quotation.IQuotationService;
import com.taim.taimbackendservice.service.staff.IStaffService;
import com.taim.taimbackendservicemodel.CreateQuotationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class QuotationController {

    private final IQuotationService quotationService;
    private final ICustomerService customerService;
    private final IStaffService staffService;
    private final IProductService productService;
    private final TransactionCalculationHelper transactionCalculationHelper;

    @Autowired
    public QuotationController(IQuotationService quotationService,
                               ICustomerService customerService,
                               IStaffService staffService,
                               IProductService productService,
                               TransactionCalculationHelper transactionCalculationHelper) {
        this.quotationService = quotationService;
        this.customerService = customerService;
        this.staffService = staffService;
        this.productService = productService;
        this.transactionCalculationHelper = transactionCalculationHelper;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/quotations",
            params = "action=getAll"
    )
    public List<Quotation> getAllQuotations() {
        return this.quotationService.getAll();
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/quotations",
            params = "action=save")
    public Quotation saveQuotation(@RequestBody CreateQuotationDTO createQuotationDTO) {
        Optional<Quotation> quotationOptional = Optional
                .ofNullable(this.quotationService.getByQuotationId(createQuotationDTO.getQuotationId()));
        Quotation newQuotation = null;
        if (!quotationOptional.isPresent()) {
            newQuotation = new Quotation();
            // Retrieve customer detail
            Customer quotationCustomer = this.customerService.getById(createQuotationDTO.getCustomerId());
            // Retrieve staff detail
            Staff quotationStaff = this.staffService.getById(createQuotationDTO.getStaffId());
            newQuotation.setCustomer(quotationCustomer);
            newQuotation.setStaff(quotationStaff);
            newQuotation.setNote(createQuotationDTO.getNote());
            newQuotation.setStatus(TransactionStatus.getStatus(createQuotationDTO.getStatus()));
            newQuotation.setQuotationId(createQuotationDTO.getQuotationId());
            newQuotation.setTransactionType(TransactionType.QUOTATION);
            newQuotation.setQuotationDate(createQuotationDTO.getQuotationDate());
            newQuotation.setDueDate(createQuotationDTO.getDueDate());

            List<TransactionDetail> transactionDetailList = createQuotationDTO.getCreateQuotationDetailDTOList()
                    .stream()
                    .map(createQuotationDetailDTO -> {
                        TransactionDetail transactionDetail = new TransactionDetail();
                        transactionDetail.setQuantity(createQuotationDetailDTO.getQuantity());
                        transactionDetail.setDiscount(createQuotationDetailDTO.getDiscount());
                        transactionDetail.setUnitPrice(createQuotationDetailDTO.getUnitPrice());
                        transactionDetail.setNote(createQuotationDetailDTO.getNote());
                        transactionDetail.setProduct(
                                // Retrieve product details
                                this.productService.getProductBySku(createQuotationDetailDTO.getProductSku()));
                        transactionCalculationHelper.calculateTransactionDetailTotal(transactionDetail);

                        return transactionDetail;
                    }).collect(Collectors.toList());

            newQuotation.setTransactionDetails(transactionDetailList);
        } else {

        }

        return this.quotationService.save(newQuotation);
    }
}
