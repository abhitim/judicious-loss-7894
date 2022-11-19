package com.mpay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpay.exceptions.CustomerException;
import com.mpay.exceptions.InsufficientFundException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.BankAccount;
import com.mpay.model.Customer;
import com.mpay.model.Wallet;
import com.mpay.services.WalletService;

@RestController
@RequestMapping("/mpay/wallet")
public class WalletController {

	@Autowired
	private WalletService wService;

	@GetMapping("/view")
	public ResponseEntity<Wallet> showWalletBalanceHandler(@RequestParam String key)
			throws CustomerException, CredentialsNotValidException {
		Wallet wallet = wService.showBalance(key);

		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

	@PostMapping("/transfer")
	public ResponseEntity<String> fundTransferHandler(@RequestParam String to, @RequestParam double amount,
			@RequestParam String key) throws CustomerException, InsufficientFundException, CredentialsNotValidException {
		String s = wService.fundTransfer(to, amount, key);

		return new ResponseEntity<String>(s, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestParam String key, @RequestBody Customer customer)
			throws CustomerException, CredentialsNotValidException {
		Customer cus = wService.updateAccount(customer, key);
		return new ResponseEntity<Customer>(cus, HttpStatus.OK);
	}

	@PostMapping("/deposit")
	public ResponseEntity<Wallet> AddMoneyHandler(@RequestParam double amount, @RequestParam String key,
			@RequestBody BankAccount acc) throws CustomerException, WalletException, CredentialsNotValidException {
		Wallet wallet = wService.addMoney(key, amount, acc);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

}
