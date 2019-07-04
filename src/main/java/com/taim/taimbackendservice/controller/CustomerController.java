package com.taim.taimbackendservice.controller;


import com.taim.taimbackendservice.manager.customer.CustomerManager;
import com.taim.taimbackendservicemodel.CreateCustomerDTO;
import com.taim.taimbackendservicemodel.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerManager customerManager;

    @Autowired
    public CustomerController(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @GetMapping(
            value = "/customers",
            params = "action=getAll"
    )
    public List<CustomerDTO> getAllCustomers() {
        return customerManager.getAllCustomers();
    }

    @PostMapping(
            value = "/customers",
            params = "action=save"
    )
    public void saveCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        customerManager.saveCustomer(createCustomerDTO);
    }

    @GetMapping(
            value = "/customers",
            params = "action=getById"
    )
    public CustomerDTO getCustomerById(@RequestParam("id") Long customerId) {
        return customerManager.getCustomerByCustomerId(customerId);
    }
}
