package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.dao.CustomerRepository;
import com.masai.model.BeneficiaryDetails;
import com.masai.model.Customer;
import com.masai.model.SigninDto;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository repo;

	@Override
	public BeneficiaryDetails registerCustomer(Customer customer)throws CustomerException {
		Customer existCust=repo.findByMobileNumber(customer.getMobileNumber());
		if(existCust!=null) {
			throw new CustomerException("Customer already registered with mobile number : "+customer.getMobileNumber());
		}
		repo.save(customer);
		return new BeneficiaryDetails(customer.getMobileNumber(), customer.getName());
	}

	@Override
	public BeneficiaryDetails signinCustomer(SigninDto signin) throws CustomerException {
		Customer c=repo.findByMobileNumber(signin.getMobileNumber());
		if(c==null) {
			throw new CustomerException("Customer not registered");
		}

		if(!c.getPassword().equals(signin.getPassword())) {
			throw new CustomerException("Password is incorrect..");
		}
		return new BeneficiaryDetails(c.getMobileNumber(),c.getName());
	}




	
	
}
