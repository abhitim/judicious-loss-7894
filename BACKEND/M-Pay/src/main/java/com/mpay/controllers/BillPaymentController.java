package com.mpay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpay.exceptions.InsufficientFundException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.BillPayment;
import com.mpay.repositories.CustomerRepo;
import com.mpay.repositories.TransactionRepo;
import com.mpay.repositories.WalletRepo;
import com.mpay.services.BillPaymentService;

@RestController
@RequestMapping("/mpay/billservice")
public class BillPaymentController {

	@Autowired
	BillPaymentService billservice;

	@Autowired
	WalletRepo wr;

	@Autowired
	CustomerRepo cr;

	@Autowired
	TransactionRepo tr;

	@PostMapping("/add")
	public ResponseEntity<BillPayment> addNewBillHandler(@RequestParam String key, @RequestBody BillPayment bill)
			throws CredentialsNotValidException, InsufficientFundException {
		BillPayment savedBill = billservice.addBillPayment(bill, key);
		return new ResponseEntity<BillPayment>(savedBill, HttpStatus.CREATED);
	}

	@GetMapping("/viewall")
	public ResponseEntity<List<BillPayment>> getAllBillsHandler(@RequestParam String key)
			throws CredentialsNotValidException, WalletException {
		List<BillPayment> billList = billservice.viewAllBillPayments(key);
		return new ResponseEntity<List<BillPayment>>(billList, HttpStatus.OK);
	}
}
