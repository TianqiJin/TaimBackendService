package com.taim.taimbackendservice.model.basemodels;

import com.taim.taimbackendservice.model.Customer;
import com.taim.taimbackendservice.model.Staff;
import com.taim.taimbackendservice.model.TransactionDetail;
import com.taim.taimbackendservice.model.enums.TransactionStatus;
import com.taim.taimbackendservice.model.enums.TransactionType;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class TransactionBaseModel extends BaseModel{
    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(name = "total_tax", nullable = false)
    private BigDecimal totalTax;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "transaction_id_fk")
    @Fetch(FetchMode.SUBSELECT)
    private List<TransactionDetail> transactionDetails;

    @Column(name = "ref_id")
    private long refId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column
    private String note;

    @Column(name = "bill_to_address")
    private String billToAddress;

}
