package com.taim.taimbackendservice.manager.transaction;

import com.taim.taimbackendservice.model.enums.TaxType;
import com.taim.taimbackendservicemodel.CreateQuotationDTO;
import com.taim.taimbackendservicemodel.QuotationDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TransactionManager {
    QuotationDTO getByQuotationId(String quotationId);
    List<QuotationDTO> getAllQuotations();
    void saveAndSubmitQuotation(CreateQuotationDTO createQuotationDTO);
    Map<TaxType, List<BigDecimal>> getAllowedTaxRateMap();
    List<QuotationDTO> getQuotationListByCustomerId(Long id);
}
