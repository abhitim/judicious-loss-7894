package com.mpay.controllers;

import java.util.List;

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

import com.mpay.exceptions.BankAccountException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.BankAccount;
import com.mpay.model.Wallet;
import com.mpay.repositories.WalletRepo;
import com.mpay.services.AccountService;

@RestController
@RequestMapping("/mpay/bankservice")
public class BankAccountController {

	@Autowired
	private AccountService accService;

	@Autowired
	private WalletRepo wRepo;

	@PostMapping("/accounts")
	public ResponseEntity<Wallet> addNewBankAccountHandler(@RequestParam String key, @RequestBody BankAccount account)
			throws BankAccountException, CredentialsNotValidException {
		Wallet wallet = accService.addAccount(account, key);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.CREATED);
	}

	@DeleteMapping("/accounts")
	public ResponseEntity<Wallet> removeBankAccountHandler(@RequestParam String key, @RequestBody BankAccount account)
			throws BankAccountException, WalletException, CredentialsNotValidException {
		Wallet wallet = accService.removeAccount(account.getAccountNo(), key);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

	@GetMapping("/accounts")
	public ResponseEntity<List<BankAccount>> getAllBankAccountHandler(@RequestParam String key)
			throws BankAccountException, CredentialsNotValidException {
		List<BankAccount> accList = accService.viewAccounts(key);
		return new ResponseEntity<List<BankAccount>>(accList, HttpStatus.OK);
	}
}
