package com.taim.taimbackendservice.helper;

import com.taim.taimbackendservice.model.TaxInfo;
import com.taim.taimbackendservice.model.TransactionDetail;
import com.taim.taimbackendservice.model.basemodels.TransactionBaseModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionCalculationHelper {

    public void calculateTransactionDetailTotal(TransactionDetail transactionDetail) {
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

    public void calculateTransactionTotal(TransactionBaseModel transactionBaseModel) {
        BigDecimal subtotal = transactionBaseModel.getTransactionDetails().size() == 0
                ? BigDecimal.ZERO
                : transactionBaseModel.getTransactionDetails().stream()
                .map(TransactionDetail::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal total = transactionBaseModel.getTransactionDetails().size() == 0
                ? BigDecimal.ZERO
                : transactionBaseModel.getTransactionDetails().stream()
                .map(TransactionDetail::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        transactionBaseModel.setSubtotal(subtotal);
        transactionBaseModel.setTotal(total);
    }

}
