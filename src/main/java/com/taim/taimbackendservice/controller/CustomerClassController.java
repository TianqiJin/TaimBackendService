package com.taim.taimbackendservice.controller;

import com.taim.taimbackendservice.model.CustomerClass;
import com.taim.taimbackendservice.service.customerclass.ICustomerClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerClassController {

    private final ICustomerClassService customerClassService;

    @Autowired
    public CustomerClassController(ICustomerClassService customerClassService) {
        this.customerClassService = customerClassService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/customerclasses",
            params = "action=getAll"
    )
    public List<CustomerClass> getCustomerOverview() {
        return customerClassService.getAll();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/customerclasses",
            params = "action=getByFilter"
    )
    public CustomerClass getCustomerClassByCustomerClassName(@RequestParam("name") String customerClassName) {
        return customerClassService.getByCustomerClassName(customerClassName);
    }
}
