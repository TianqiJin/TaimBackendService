package com.taim.taimbackendservice.repository;

import com.taim.taimbackendservice.model.CustomerClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerClassRepository extends JpaRepository<CustomerClass, Long> {
    CustomerClass findCustomerClassByCustomerClassName(String customerClassName);
}
