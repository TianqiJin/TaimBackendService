package com.taim.taimbackendservice.manager.customer;

import com.taim.taimbackendservice.mapper.CreateCustomerDTOMapper;
import com.taim.taimbackendservice.mapper.CustomerDTOMapper;
import com.taim.taimbackendservice.model.Customer;
import com.taim.taimbackendservice.repository.CustomerRepository;
import com.taim.taimbackendservicemodel.CreateCustomerDTO;
import com.taim.taimbackendservicemodel.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManagerImpl implements CustomerManager {

    private final CustomerRepository customerRepository;
    private final CreateCustomerDTOMapper createCustomerDTOMapper;
    private final CustomerDTOMapper customerDTOMapper;

    @Autowired
    public CustomerManagerImpl(CustomerRepository customerRepository,
                               CreateCustomerDTOMapper createCustomerDTOMapper,
                               CustomerDTOMapper customerDTOMapper) {
        this.customerRepository = customerRepository;
        this.createCustomerDTOMapper = createCustomerDTOMapper;
        this.customerDTOMapper = customerDTOMapper;
    }

    @Override
    @Transactional
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = this.customerRepository.findAll();
        return customerList.stream()
                .map(customerDTOMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = this.createCustomerDTOMapper.map(createCustomerDTO);
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public CustomerDTO getCustomerByCustomerId(Long customerId) {
        Customer customer = this.customerRepository.getOne(customerId);

        return this.customerDTOMapper.convert(customer);
    }
}
