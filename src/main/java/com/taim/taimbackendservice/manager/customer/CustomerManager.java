package com.taim.taimbackendservice.manager.customer;


import com.taim.taimbackendservicemodel.CreateCustomerDTO;
import com.taim.taimbackendservicemodel.CustomerDTO;

import java.util.List;

public interface CustomerManager {
    List<CustomerDTO> getAllCustomers();
    void saveCustomer(CreateCustomerDTO createCustomerDTO);
    CustomerDTO getCustomerByCustomerId(Long customerId);
}
