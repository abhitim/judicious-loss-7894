package com.masai.service;



import com.masai.model.BeneficiaryDetails;
import com.masai.model.Customer;
import com.masai.model.SigninDto;
import com.masai.Exceptions.CustomerException;

public interface CustomerService {
	public BeneficiaryDetails registerCustomer(Customer customer)throws CustomerException;
	public BeneficiaryDetails signinCustomer(SigninDto signin)throws CustomerException;
}
