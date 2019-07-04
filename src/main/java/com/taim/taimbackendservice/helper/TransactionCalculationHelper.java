package com.taim.taimbackendservice.helper;

import com.taim.taimbackendservice.model.Quotation;
import com.taim.taimbackendservice.model.TaxInfo;
import com.taim.taimbackendservice.model.TransactionDetail;
import com.taim.taimbackendservice.model.basemodels.TransactionBaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class TransactionCalculationHelper {

    public void calculateTaxInfo(TaxInfo taxInfo, BigDecimal quantity, BigDecimal unitPrice) {
        BigDecimal taxAmountByOne = taxInfo.getTaxRate().multiply(unitPrice);
        BigDecimal taxAmount = taxInfo.getTaxRate().multiply(unitPrice).multiply(quantity);

        taxInfo.setTaxAmount(taxAmount);
        taxInfo.setTaxAmountByOne(taxAmountByOne);
    }

    public void calculateTransactionDetailTotal(TransactionDetail transactionDetail) {
        log.info(transactionDetail.toString());
        BigDecimal subtotal = transactionDetail.getQuantity()
                .multiply(transactionDetail.getUnitPrice())
                .multiply(BigDecimal.ONE
                        .subtract(transactionDetail.getDiscount()));

        BigDecimal taxTotal = transactionDetail.getTaxInfos().size() == 0
            ? BigDecimal.ZERO
            : transactionDetail.getTaxInfos().stream()
                .map(TaxInfo::getTaxAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        transactionDetail.setSubtotal(subtotal);
        transactionDetail.setTotal(subtotal.add(taxTotal));

    }

    public void calculateTransactionTotal(Quotation quotation) {
        System.out.println(quotation.getTransactionDetails());
        BigDecimal subtotal = quotation.getTransactionDetails().size() == 0
                ? BigDecimal.ZERO
                : quotation.getTransactionDetails().stream()
                .map(TransactionDetail::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal total = quotation.getTransactionDetails().size() == 0
                ? BigDecimal.ZERO
                : quotation.getTransactionDetails().stream()
                .map(TransactionDetail::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal taxTotal = quotation.getTransactionDetails().size() == 0
                ? BigDecimal.ZERO
                : quotation.getTransactionDetails().stream()
                .map(transactionDetail -> transactionDetail.getTaxInfos().stream()
                        .map(TaxInfo::getTaxAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        quotation.setSubtotal(subtotal);
        quotation.setTotal(total);
        quotation.setTotalTax(taxTotal);
    }

}
