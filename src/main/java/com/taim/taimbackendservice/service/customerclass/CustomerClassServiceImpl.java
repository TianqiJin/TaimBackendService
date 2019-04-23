package com.taim.taimbackendservice.service.customerclass;

import com.taim.taimbackendservice.model.CustomerClass;
import com.taim.taimbackendservice.repository.CustomerClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerClassServiceImpl implements ICustomerClassService {

    private final CustomerClassRepository customerClassRepository;

    @Autowired
    public CustomerClassServiceImpl(CustomerClassRepository customerClassRepository) {
        this.customerClassRepository = customerClassRepository;
    }

    @Override
    public List<CustomerClass> getAll() {
        return customerClassRepository.findAll();
    }

    @Override
    public CustomerClass getById(long id) {
        return customerClassRepository.getOne(id);
    }

    @Override
    public CustomerClass save(CustomerClass customerClass) {
        return customerClassRepository.saveAndFlush(customerClass);
    }

    @Override
    public CustomerClass update(CustomerClass customerClass) {
        return customerClassRepository.saveAndFlush(customerClass);
    }

    @Override
    public CustomerClass getByCustomerClassName(String customerClassName) {
        return customerClassRepository.findCustomerClassByCustomerClassName(customerClassName);
    }
}
