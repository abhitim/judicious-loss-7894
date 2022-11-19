package com.mpay.services;

import java.util.List;

import com.mpay.exceptions.CustomerException;
import com.mpay.model.Customer;

public interface CustomerService {
	
	public Customer RegisterCustomer(Customer customer) throws CustomerException;
}

