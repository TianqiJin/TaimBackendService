package com.taim.taimbackendservice.manager.customerclass;

import com.taim.taimbackendservice.mapper.CustomerClassDTOMapper;
import com.taim.taimbackendservice.model.CustomerClass;
import com.taim.taimbackendservice.repository.CustomerClassRepository;
import com.taim.taimbackendservicemodel.CustomerClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerClassManagerImpl implements CustomerClassManager {

    private final CustomerClassRepository customerClassRepository;
    private final CustomerClassDTOMapper customerClassDTOMapper;

    @Autowired
    public CustomerClassManagerImpl(CustomerClassRepository customerClassRepository,
                                    CustomerClassDTOMapper customerClassDTOMapper) {
        this.customerClassRepository = customerClassRepository;
        this.customerClassDTOMapper = customerClassDTOMapper;
    }

    @Override
    public List<CustomerClassDTO> getAll() {
        return customerClassRepository.findAll().stream()
                .map(this.customerClassDTOMapper :: convert)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerClassDTO getByCustomerClassName(String customerClassName) {
        CustomerClass customerClass =  customerClassRepository.findCustomerClassByCustomerClassName(customerClassName);
        return this.customerClassDTOMapper.convert(customerClass);
    }
}
