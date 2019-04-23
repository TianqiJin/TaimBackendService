package com.taim.taimbackendservice.service.quotation;

import com.taim.taimbackendservice.model.Quotation;
import com.taim.taimbackendservice.service.IBaseService;

public interface IQuotationService extends IBaseService<Quotation> {
    Quotation getByQuotationId(String quotationId);
}
