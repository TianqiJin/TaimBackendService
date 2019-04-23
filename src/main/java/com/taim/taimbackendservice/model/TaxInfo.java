package com.taim.taimbackendservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taim.taimbackendservice.model.basemodels.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "taxinfo")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaxInfo extends BaseModel {

    @Column(name = "tax_type")
    private String taxType;

    @Column(name = "tax_rate")
    private BigDecimal taxRate;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    @Column(name = "tax_amount_by_one")
    private BigDecimal taxAmountByOne;
}
