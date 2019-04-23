package com.taim.taimbackendservice.repository;

import com.taim.taimbackendservice.model.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    Quotation getByQuotationId(String quotationId);
}
