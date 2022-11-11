package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Query("from Customer where Mobile=?1")
	public Customer findByMobile(String mobileNo);
}
