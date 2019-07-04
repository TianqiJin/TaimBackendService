package com.taim.taimbackendservice.manager.customerclass;

import com.taim.taimbackendservicemodel.CustomerClassDTO;

import java.util.List;

public interface CustomerClassManager {
    CustomerClassDTO getByCustomerClassName(String customerClassName);
    List<CustomerClassDTO> getAll();
}
