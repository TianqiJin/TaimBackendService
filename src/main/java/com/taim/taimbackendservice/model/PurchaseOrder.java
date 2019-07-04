package com.taim.taimbackendservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taim.taimbackendservice.model.basemodels.TransactionBaseModel;
import com.taim.taimbackendservice.model.enums.PaymentStatus;
import com.taim.taimbackendservice.model.enums.TransactionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchase_order")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PurchaseOrder extends TransactionBaseModel {
    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(name = "total_tax", nullable = false)
    private BigDecimal totalTax;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id_fk")
    private Staff staff;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id_fk")
    private Customer customer;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDueDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "purchase_order_id_fk")
    @Fetch(FetchMode.SUBSELECT)
    private List<TransactionDetail> transactionDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "purchase_order_id_fk")
    @Fetch(FetchMode.SUBSELECT)
    private List<Payment> payments;

    @Column(name = "is_finalized", nullable = false)
    private boolean finalized;

    @Column
    private String note;
}
