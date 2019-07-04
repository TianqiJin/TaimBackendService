package com.taim.taimbackendservice.mapper;

import com.taim.taimbackendservice.model.Vendor;
import com.taim.taimbackendservice.model.enums.UserType;
import com.taim.taimbackendservicemodel.CreateVendorDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateVendorDTOMapper {

    public Vendor map(CreateVendorDTO createVendorDTO) {
        Vendor vendor = new Vendor();
        vendor.setGstNumber(createVendorDTO.getGstNumber());
        vendor.setFirstName(createVendorDTO.getFirstName());
        vendor.setLastName(createVendorDTO.getLastName());
        vendor.setPhone(createVendorDTO.getPhone());
        vendor.setEmail(createVendorDTO.getEmail());
        vendor.setUserType(UserType.getUserType(createVendorDTO.getUserType()));

        return vendor;
    }
}
