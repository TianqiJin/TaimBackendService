package com.taim.taimbackendservice.controller;

import com.taim.taimbackendservice.mapper.CreateCustomerDTOMapper;
import com.taim.taimbackendservice.mapper.CustomerDTOMapper;
import com.taim.taimbackendservice.model.Customer;
import com.taim.taimbackendservice.service.customer.ICustomerService;
import com.taim.taimbackendservicemodel.CreateCustomerDTO;
import com.taim.taimbackendservicemodel.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private final ICustomerService customerService;
    private final CustomerDTOMapper customerDTOMapper;
    @Autowired
    private CreateCustomerDTOMapper createCustomerDTOMapper;

    @Autowired
    public CustomerController(ICustomerService customerService, CustomerDTOMapper customerDTOMapper) {
        this.customerService = customerService;
        this.customerDTOMapper = customerDTOMapper;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/customers",
            params = "action=getAll"
    )
    public List<CustomerDTO> getCustomerOverview() {
        return customerService.getAll().stream()
                .map(customerDTOMapper::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/customers",
            params = "action=save"
    )
    public Customer saveCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        return customerService.save(this.createCustomerDTOMapper.reverse().convert(createCustomerDTO));
    }
}
