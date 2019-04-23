package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.Customer;
import com.taim.taimbackendservicemodel.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerDTOMapper extends Converter<Customer, CustomerDTO> {

    private final AddressDTOMapper addressDTOMapper;

    private final CustomerClassDTOMapper customerClassDTOMapper;

    @Autowired
    public CustomerDTOMapper(AddressDTOMapper addressDTOMapper, CustomerClassDTOMapper customerClassDTOMapper) {
        this.addressDTOMapper = addressDTOMapper;
        this.customerClassDTOMapper = customerClassDTOMapper;
    }

    @Override
    protected CustomerDTO doForward(Customer customer) {
        return CustomerDTO.builder()
                .addresses(customer.getAddress().stream().map(address -> addressDTOMapper.convert(address))
                        .collect(Collectors.toList()))
                .customerClass(customerClassDTOMapper.convert(customer.getCustomerClass()))
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .storeCredit(customer.getStoreCredit())
                .pstNumber(customer.getPstNumber())
                .userType(customer.getUserType().getValue())
                .id(customer.getId())
                .build();
    }

    @Override
    protected Customer doBackward(CustomerDTO customerDTO) {
        throw new UnsupportedOperationException();
    }
}
