package com.taim.taimbackendservice.service.customerclass;

import com.taim.taimbackendservice.model.CustomerClass;
import com.taim.taimbackendservice.service.IBaseService;

import java.util.List;

public interface ICustomerClassService extends IBaseService<CustomerClass> {
    CustomerClass getByCustomerClassName(String customerClassName);
}
