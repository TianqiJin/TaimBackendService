package com.taim.taimbackendservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taim.taimbackendservice.model.basemodels.TransactionBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quotation")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Quotation extends TransactionBaseModel {

    @Column(name = "quotation_id")
    private String quotationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "quotation_date", nullable = false)
    private Date quotationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "bill_to_address")
    private String billToAddress;

    @Column(name = "bill_from_address")
    private String billFromAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "quotation_id_fk")
    @Fetch(FetchMode.SUBSELECT)
    private List<TransactionDetail> transactionDetails;
}
