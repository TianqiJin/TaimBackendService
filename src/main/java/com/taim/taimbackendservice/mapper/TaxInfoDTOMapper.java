package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.TaxInfo;
import com.taim.taimbackendservicemodel.TaxInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class TaxInfoDTOMapper extends Converter<TaxInfo, TaxInfoDTO> {

    @Override
    protected TaxInfoDTO doForward(TaxInfo taxInfo) {
        return TaxInfoDTO.builder()
                .id(taxInfo.getId())
                .taxAmount(taxInfo.getTaxAmount())
                .taxRate(taxInfo.getTaxRate())
                .taxAmountByOne(taxInfo.getTaxAmountByOne())
                .taxType(taxInfo.getTaxType())
                .build();
    }

    @Override
    protected TaxInfo doBackward(TaxInfoDTO taxInfoDTO) {
        throw new UnsupportedOperationException();
    }
}
