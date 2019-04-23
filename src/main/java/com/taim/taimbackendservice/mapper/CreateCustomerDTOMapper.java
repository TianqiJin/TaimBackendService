package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.Customer;
import com.taim.taimbackendservice.model.basemodels.UserBaseModel;
import com.taim.taimbackendservice.service.customerclass.ICustomerClassService;
import com.taim.taimbackendservicemodel.CreateCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerDTOMapper extends Converter<Customer, CreateCustomerDTO> {

    private final ICustomerClassService customerClassService;

    @Autowired
    public CreateCustomerDTOMapper(ICustomerClassService customerClassService) {
        this.customerClassService = customerClassService;
    }

    @Override
    protected CreateCustomerDTO doForward(Customer customer) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Customer doBackward(CreateCustomerDTO createCustomerDTO) {
        Customer customer = new Customer();
        customer.setPstNumber(createCustomerDTO.getPstNumber());
        customer.setFirstName(createCustomerDTO.getFirstName());
        customer.setLastName(createCustomerDTO.getLastName());
        customer.setCustomerClass(
                this.customerClassService.getByCustomerClassName(createCustomerDTO.getCustomerClassName()));
        customer.setPhone(createCustomerDTO.getPhone());
        customer.setEmail(createCustomerDTO.getEmail());
        customer.setUserType(UserBaseModel.UserType.getUserType(createCustomerDTO.getUserType()));
        customer.setStoreCredit(createCustomerDTO.getStoreCredit());


        return customer;
    }
}
