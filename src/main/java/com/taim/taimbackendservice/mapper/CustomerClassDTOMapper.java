package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.CustomerClass;
import com.taim.taimbackendservicemodel.CustomerClassDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerClassDTOMapper extends Converter<CustomerClass, CustomerClassDTO> {

    @Override
    protected CustomerClassDTO doForward(CustomerClass customerClass) {
        return CustomerClassDTO.builder()
                .customerClassName(customerClass.getCustomerClassName())
                .customerDiscount(customerClass.getCustomerDiscount())
                .id(customerClass.getId())
                .build();
    }

    @Override
    protected CustomerClass doBackward(CustomerClassDTO customerClassDTO) {
        throw new UnsupportedOperationException();
    }
}
