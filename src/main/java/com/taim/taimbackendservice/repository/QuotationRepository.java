package com.taim.taimbackendservice.repository;

import com.taim.taimbackendservice.model.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    Quotation getByQuotationId(String quotationId);
    List<Quotation> getQuotationsByCustomerId(Long id);
}
