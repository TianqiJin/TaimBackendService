package com.taim.taimbackendservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taim.taimbackendservice.model.basemodels.UserBaseModel;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by tjin on 2017-07-23.
 */
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Customer extends UserBaseModel {

    @Column(name = "store_credit")
    private BigDecimal storeCredit;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_class_id", nullable = false)
    private CustomerClass customerClass;

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "pst_num")
    private String pstNumber;
}
