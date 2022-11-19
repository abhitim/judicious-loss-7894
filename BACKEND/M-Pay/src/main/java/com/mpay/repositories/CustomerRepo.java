package com.mpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpay.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {
	
	public Customer findByMobile(String mobile);

}
