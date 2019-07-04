package com.taim.taimbackendservice.controller;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taim.taimbackendservice.manager.transaction.TransactionManager;
import com.taim.taimbackendservice.model.Invoice;
import com.taim.taimbackendservice.model.TransactionDetail;
import com.taim.taimbackendservice.model.enums.TaxType;
import com.taim.taimbackendservice.model.enums.TransactionStatus;
import com.taim.taimbackendservice.model.enums.TransactionType;
import com.taim.taimbackendservice.repository.CustomerRepository;
import com.taim.taimbackendservice.repository.InvoiceRepository;
import com.taim.taimbackendservice.repository.ProductRepository;
import com.taim.taimbackendservice.repository.StaffRepository;
import com.taim.taimbackendservicemodel.CreateQuotationDTO;
import com.taim.taimbackendservicemodel.QuotationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TransactionController {

    private final TransactionManager transactionManager;
    @Autowired
    private  InvoiceRepository invoiceRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Autowired
    public TransactionController(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    @PostMapping(
            value = "/quotations",
            params = "action=save")
    public void createNewQuotation(@RequestBody CreateQuotationDTO createQuotationDTO) {
        log.info("Received quotation creation request: {}", gson.toJson(createQuotationDTO));
        this.transactionManager.saveAndSubmitQuotation(createQuotationDTO);

    }

    @GetMapping(
            value = "/transactions",
            params = "action=getAllowedTaxRates"
    )
    public Map<TaxType, List<BigDecimal>> getAllowedTaxRatesMap() {
        return this.transactionManager.getAllowedTaxRateMap();
    }

    @GetMapping(
            value = "/quotations",
            params = "action=getByCustomerId"
    )
    public List<QuotationDTO> getQuotationDTOListByCustomerId (@RequestParam("customerId") Long customerId) {
        return this.transactionManager.getQuotationListByCustomerId(customerId);
    }

    @GetMapping(
            value = "/quotations",
            params = "action=getByQuotationId"
    )
    public QuotationDTO getQuotationDTOByQuotationId (@RequestParam("quotationId") String quotationId) {
        return this.transactionManager.getByQuotationId(quotationId);
    }

    @GetMapping(
            value = "/quotations",
            params = "action=getAll"
    )
    public List<QuotationDTO> getAllQuotationDTOs () {
        return this.transactionManager.getAllQuotations();
    }

    @GetMapping(
            value = "/invoice",
            params = "action=tmpCreateNew"
    )
    public void testCreateNewInvoice () {
        Invoice invoice = new Invoice();
        invoice.setCustomer(customerRepository.getOne(1L));
        invoice.setStaff(staffRepository.getOne(1L));
        invoice.setInvoiceId(new Date().toString());
        invoice.setTransactionType(TransactionType.QUOTATION);

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setProduct(this.productRepository.getOne(1L));

        invoice.setTransactionDetails(ImmutableList.of(transactionDetail));

        this.invoiceRepository.save(invoice);
    }
}
