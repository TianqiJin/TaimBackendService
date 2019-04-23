package com.taim.taimbackendservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

/**
 * Created by tjin on 2017-07-28.
 */
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Product extends BaseModel {

    @Column(unique = true, nullable = false)
    private String sku;

    @Column
    private String description;

    @Column
    private BigDecimal length;

    @Column
    private BigDecimal width;

    @Column
    private BigDecimal height;

    @Column(name = "display_name")
    private String displayName;

    @Column
    private String texture;

    @Column
    private String color;

    @Column(name = "total_num")
    private BigDecimal totalNum;

    @Column(name = "total_num_virtual")
    private BigDecimal totalNumVirtual;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "piece_per_box")
    private BigDecimal piecePerBox;
}
