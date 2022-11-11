package com.masai.service;

import com.masai.Exceptions.CustomerException;
import com.masai.dto.CustomerSignInDto;
import com.masai.model.Customer;

public interface CustomerService {
	public Customer registerCustomer(Customer customer)throws CustomerException;
	public Customer validateCustomer(CustomerSignInDto customer)throws CustomerException;
}
