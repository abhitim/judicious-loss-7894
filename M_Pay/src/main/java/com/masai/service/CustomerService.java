package com.masai.service;

import com.masai.Exceptions.CustomerException;

import com.masai.model.Customer;
import com.masai.model.SigninDto;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer)throws CustomerException;
	public Customer validateCustomer(SigninDto customer)throws CustomerException;
}
