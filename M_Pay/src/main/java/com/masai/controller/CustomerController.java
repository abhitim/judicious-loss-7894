package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.CustomerException;

import com.masai.model.Customer;
import com.masai.model.SigninDto;
import com.masai.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@GetMapping("/")
	public String welcome() {
		return "Hello world..";
	}
	
	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer c) throws CustomerException{
		return new ResponseEntity<Customer>(service.registerCustomer(c),HttpStatus.CREATED);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<Customer> validateCustomer(@Valid @RequestBody SigninDto dto) throws CustomerException{
		return new ResponseEntity<Customer>(service.validateCustomer(dto),HttpStatus.OK);
	}
}
