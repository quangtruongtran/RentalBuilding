package com.RentalBuilding.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RentalBuilding.Entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	CustomerEntity findByCustomerIdentifyNumber(String customerIdentifyNumber);
}
