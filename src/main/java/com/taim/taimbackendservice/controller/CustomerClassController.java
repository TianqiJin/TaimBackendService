package com.taim.taimbackendservice.controller;

import com.taim.taimbackendservice.manager.customerclass.CustomerClassManager;
import com.taim.taimbackendservicemodel.CustomerClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerClassController {

    private final CustomerClassManager customerClassManager;

    @Autowired
    public CustomerClassController(CustomerClassManager customerClassManager) {
        this.customerClassManager = customerClassManager;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/customerclasses",
            params = "action=getAll"
    )
    public List<CustomerClassDTO> getCustomerOverview() {
        return customerClassManager.getAll();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/customerclasses",
            params = "action=getByFilter"
    )
    public CustomerClassDTO getCustomerClassByCustomerClassName(@RequestParam("name") String customerClassName) {
        return customerClassManager.getByCustomerClassName(customerClassName);
    }
}
