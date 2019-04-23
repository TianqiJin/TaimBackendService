package com.taim.taimbackendservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taim.taimbackendservice.model.basemodels.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by tjin on 2017-07-28.
 */
@Entity
@Table(name = "transaction_detail")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransactionDetail extends BaseModel {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(nullable = false)
    private BigDecimal total;

    @Column
    private BigDecimal discount;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "transaction_detail_id_fk")
    @Fetch(FetchMode.SUBSELECT)
    private List<TaxInfo> taxInfos;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column
    private String note;
}
