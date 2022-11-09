package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;
import com.masai.Exceptions.CustomerException;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	public Customer findByMobileNumber(String mobile)throws CustomerException;
}
