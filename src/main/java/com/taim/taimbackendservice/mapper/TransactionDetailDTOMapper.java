package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.TransactionDetail;
import com.taim.taimbackendservicemodel.TransactionDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TransactionDetailDTOMapper extends Converter<TransactionDetail, TransactionDetailDTO> {
    private final ProductDTOMapper productDTOMapper;
    private final TaxInfoDTOMapper taxInfoDTOMapper;

    @Autowired
    public TransactionDetailDTOMapper(ProductDTOMapper productDTOMapper, TaxInfoDTOMapper taxInfoDTOMapper) {
        this.productDTOMapper = productDTOMapper;
        this.taxInfoDTOMapper = taxInfoDTOMapper;
    }

    @Override
    protected TransactionDetailDTO doForward(TransactionDetail transactionDetail) {
        return TransactionDetailDTO.builder()
                .id(transactionDetail.getId())
                .product(productDTOMapper.convert(transactionDetail.getProduct()))
                .quantity(transactionDetail.getQuantity())
                .total(transactionDetail.getTotal())
                .discount(transactionDetail.getDiscount())
                .unitPrice(transactionDetail.getUnitPrice())
                .subtotal(transactionDetail.getSubtotal())
                .note(transactionDetail.getNote())
                .taxInfos(transactionDetail.getTaxInfos().stream()
                        .map(this.taxInfoDTOMapper::convert)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    protected TransactionDetail doBackward(TransactionDetailDTO transactionDetailDTO) {
        return null;
    }
}
