package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.BeneficiaryException;
import com.masai.model.BeneficiaryDetails;
import com.masai.model.Customer;
import com.masai.service.BeneficiaryService;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {
	@Autowired
	private BeneficiaryService service;
	
	@PostMapping("/add")
	public ResponseEntity<BeneficiaryDetails> addBeneficiary(@Valid @RequestBody BeneficiaryDetails be) throws BeneficiaryException{
		return new ResponseEntity<BeneficiaryDetails>(service.addBeneficiary(be),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<BeneficiaryDetails> deleteBeneficiary(@Valid @RequestBody BeneficiaryDetails be) throws BeneficiaryException{
		return new ResponseEntity<BeneficiaryDetails>(service.deleteBeneficiary(be),HttpStatus.OK);
	}
	@GetMapping("/get")
	public ResponseEntity<BeneficiaryDetails> veiwBeneficiary(@RequestParam String mobile) throws BeneficiaryException{
		return new ResponseEntity<BeneficiaryDetails>( service.veiwBeneficiary(mobile),HttpStatus.OK);
	}
	@PostMapping("/all")
	public ResponseEntity<List<BeneficiaryDetails>> veiwAllBeneficiaty(@Valid @RequestBody Customer cust) throws BeneficiaryException{
		return new ResponseEntity<List<BeneficiaryDetails>>(service.veiwAllBeneficiary(cust),HttpStatus.OK);
	}
}
