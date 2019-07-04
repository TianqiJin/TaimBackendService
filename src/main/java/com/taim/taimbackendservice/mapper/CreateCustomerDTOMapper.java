package com.taim.taimbackendservice.mapper;

import com.taim.taimbackendservice.manager.customerclass.CustomerClassManager;
import com.taim.taimbackendservice.model.Customer;
import com.taim.taimbackendservice.model.basemodels.UserBaseModel;
import com.taim.taimbackendservice.model.enums.UserType;
import com.taim.taimbackendservicemodel.CreateCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerDTOMapper {

    private final CustomerClassManager customerClassManager;
    private final CustomerClassDTOMapper customerClassDTOMapper;

    @Autowired
    public CreateCustomerDTOMapper(CustomerClassManager customerClassService,
                                   CustomerClassDTOMapper customerClassDTOMapper) {
        this.customerClassManager = customerClassService;
        this.customerClassDTOMapper = customerClassDTOMapper;
    }

    public Customer map(CreateCustomerDTO createCustomerDTO) {
        Customer customer = new Customer();
        customer.setPstNumber(createCustomerDTO.getPstNumber());
        customer.setFirstName(createCustomerDTO.getFirstName());
        customer.setLastName(createCustomerDTO.getLastName());
        customer.setCustomerClass(this.customerClassDTOMapper.reverse().convert(
                this.customerClassManager.getByCustomerClassName(createCustomerDTO.getCustomerClassName())));
        customer.setPhone(createCustomerDTO.getPhone());
        customer.setEmail(createCustomerDTO.getEmail());
        customer.setUserType(UserType.getUserType(createCustomerDTO.getUserType()));
        customer.setStoreCredit(createCustomerDTO.getStoreCredit());

        return customer;
    }
}
