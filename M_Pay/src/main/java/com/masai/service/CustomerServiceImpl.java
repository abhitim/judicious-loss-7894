package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.CustomerData;
import com.masai.dto.SigninDto;
import com.masai.model.Customer;
import com.masai.Exceptions.CustomerException;
import com.masai.dao.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository repo;

	@Override
	public CustomerData registerCustomer(Customer customer) throws CustomerException {
		Customer existCust=repo.findByMobileNumber(customer.getMobileNumber());
		if(existCust!=null) {
			throw new CustomerException("Customer already registered with mobile number : "+customer.getMobileNumber());
		}
		repo.save(customer);
		return new CustomerData(customer.getName(),customer.getMobileNumber());
	}

	@Override
	public CustomerData signinCustomer(SigninDto signin) throws CustomerException {
		Customer c=repo.findByMobileNumber(signin.getMobileNumber());
		if(c==null) {
			throw new CustomerException("Customer not registered");
		}

		if(!c.getPassword().equals(signin.getPassword())) {
			throw new CustomerException("Password is incorrect..");
		}
		return new CustomerData(c.getName(),c.getMobileNumber());
	}


	
	
}
