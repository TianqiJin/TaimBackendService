package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.Vendor;
import com.taim.taimbackendservice.model.basemodels.UserBaseModel;
import com.taim.taimbackendservicemodel.CreateVendorDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateVendorDTOMapper extends Converter<Vendor, CreateVendorDTO> {

    @Override
    protected CreateVendorDTO doForward(Vendor vendor) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Vendor doBackward(CreateVendorDTO createVendorDTO) {
        Vendor vendor = new Vendor();
        vendor.setGstNumber(createVendorDTO.getGstNumber());
        vendor.setFirstName(createVendorDTO.getFirstName());
        vendor.setLastName(createVendorDTO.getLastName());
        vendor.setPhone(createVendorDTO.getPhone());
        vendor.setEmail(createVendorDTO.getEmail());
        vendor.setUserType(UserBaseModel.UserType.getUserType(createVendorDTO.getUserType()));

        return vendor;
    }
}
