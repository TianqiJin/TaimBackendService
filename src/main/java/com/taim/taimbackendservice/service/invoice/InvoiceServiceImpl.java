package com.taim.taimbackendservice.service.invoice;

import com.taim.taimbackendservice.model.Invoice;
import com.taim.taimbackendservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getById(long id) {
        return invoiceRepository.getOne(id);
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.saveAndFlush(invoice);
    }

    @Override
    public Invoice update(Invoice invoice) {
        return invoiceRepository.saveAndFlush(invoice);
    }
}
