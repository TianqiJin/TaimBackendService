package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.Staff;
import com.taim.taimbackendservicemodel.StaffDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StaffDTOMapper extends Converter<Staff, StaffDTO> {

    private final AddressDTOMapper addressDTOMapper;

    @Autowired
    public StaffDTOMapper(AddressDTOMapper addressDTOMapper) {
        this.addressDTOMapper = addressDTOMapper;
    }


    @Override
    protected StaffDTO doForward(Staff staff) {
        return StaffDTO.builder()
                .id(staff.getId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .phone(staff.getPhone())
                .email(staff.getEmail())
                .userName(staff.getUserName())
                .password(staff.getPassword())
                .position(staff.getPosition().getValue())
                .addresses(staff.getAddress().stream()
                        .map(this.addressDTOMapper::convert)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    protected Staff doBackward(StaffDTO staffDTO) {
        throw new UnsupportedOperationException();
    }
}
