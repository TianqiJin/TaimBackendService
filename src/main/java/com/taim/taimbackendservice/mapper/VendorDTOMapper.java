package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.Vendor;
import com.taim.taimbackendservicemodel.VendorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VendorDTOMapper extends Converter<Vendor, VendorDTO> {

    private final AddressDTOMapper addressDTOMapper;

    @Autowired
    public VendorDTOMapper(AddressDTOMapper addressDTOMapper) {
        this.addressDTOMapper = addressDTOMapper;
    }

    @Override
    protected VendorDTO doForward(Vendor vendor) {
        return VendorDTO.builder()
                .addresses(vendor.getAddress().stream()
                        .map(this.addressDTOMapper::convert).collect(Collectors.toList()))
                .email(vendor.getEmail())
                .firstName(vendor.getFirstName())
                .lastName(vendor.getLastName())
                .phone(vendor.getPhone())
                .userType(vendor.getUserType().getValue())
                .id(vendor.getId())
                .build();
    }

    @Override
    protected Vendor doBackward(VendorDTO vendorDTO) {
        return null;
    }
}
