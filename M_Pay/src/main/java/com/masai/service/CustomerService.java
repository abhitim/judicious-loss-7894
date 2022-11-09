package com.masai.service;

import com.masai.dto.CustomerData;
import com.masai.dto.SigninDto;
import com.masai.model.Customer;
import com.masai.Exceptions.CustomerException;

public interface CustomerService {
	public CustomerData registerCustomer(Customer customer)throws CustomerException;
	public CustomerData signinCustomer(SigninDto signin)throws CustomerException;
}
