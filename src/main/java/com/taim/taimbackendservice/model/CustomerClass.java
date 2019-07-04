package com.taim.taimbackendservice.model;

import com.taim.taimbackendservice.model.basemodels.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "customer_class")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomerClass extends BaseModel {

    @Column(name = "customer_class_name")
    private String customerClassName;

    @Column(name = "customer_discount")
    private BigDecimal customerDiscount;

    @Column
    private String description;
}
