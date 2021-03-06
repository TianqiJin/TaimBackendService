package com.taim.taimbackendservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taim.taimbackendservice.model.basemodels.BaseModel;
import com.taim.taimbackendservice.model.basemodels.TransactionBaseModel;
import com.taim.taimbackendservice.model.enums.DeliveryStatus;
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
@Table(name = "invoice")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Invoice extends TransactionBaseModel {

    @Column(nullable=false, name = "invoice_id")
    private String invoiceId;

    @Column
    private BigDecimal subtotal;

    @Column
    private BigDecimal total;

    @Column(name = "total_tax", nullable = false)
    private BigDecimal totalTax;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id_fk")
    private Customer customer;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "delivery_status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Column(name = "payment_due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDueDate;

    @Column(name = "delivery_due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDueDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id_fk")
    @Fetch(FetchMode.SUBSELECT)
    private List<TransactionDetail> transactionDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id_fk")
    @Fetch(FetchMode.SUBSELECT)
    private List<Payment> payments;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "invoice_id_fk")
    @Fetch(FetchMode.SUBSELECT)
    private List<Delivery> deliveries;

    @Column(name = "is_finalized", nullable = false)
    private boolean finalized;

    @Column
    private String note;
}
