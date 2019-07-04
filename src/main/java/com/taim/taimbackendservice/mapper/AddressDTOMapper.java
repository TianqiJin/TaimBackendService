package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.Address;
import com.taim.taimbackendservicemodel.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressDTOMapper extends Converter<Address, AddressDTO> {

    @Override
    protected AddressDTO doForward(Address address) {
        return AddressDTO.builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .province(address.getProvince())
                .streetNum(address.getStreetNum())
                .id(address.getId())
                .build();
    }

    @Override
    protected Address doBackward(AddressDTO addressDTO) {
        throw new UnsupportedOperationException();
    }
}
