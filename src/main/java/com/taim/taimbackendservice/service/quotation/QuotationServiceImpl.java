package com.taim.taimbackendservice.service.quotation;

import com.taim.taimbackendservice.model.Quotation;
import com.taim.taimbackendservice.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationServiceImpl implements IQuotationService {

    private final QuotationRepository quotationRepository;

    @Autowired
    public QuotationServiceImpl(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    @Override
    public List<Quotation> getAll() {
        return quotationRepository.findAll();
    }

    @Override
    public Quotation getById(long id) {
        return quotationRepository.getOne(id);
    }

    @Override
    public Quotation save(Quotation quotation) {
        return quotationRepository.saveAndFlush(quotation);
    }

    @Override
    public Quotation update(Quotation quotation) {
        return quotationRepository.saveAndFlush(quotation);
    }

    @Override
    public Quotation getByQuotationId(String quotationId) {
        return quotationRepository.getByQuotationId(quotationId);
    }
}
