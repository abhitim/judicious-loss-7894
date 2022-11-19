package com.mpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpay.exceptions.CustomerException;
import com.mpay.model.Customer;
import com.mpay.services.CustomerService;

@RestController
@RequestMapping("/mpay")
public class CustomerController {
	@Autowired
	private CustomerService uService;

	@PostMapping("/register")
	public ResponseEntity<Customer> registerAccountHandler(@RequestBody Customer customer) throws CustomerException {
		Customer cus = uService.RegisterCustomer(customer);
		return new ResponseEntity<Customer>(cus, HttpStatus.OK);
	}
}
