package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor

@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer walletId;
	private Integer balance;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Customer customer;
	 
	@OneToOne(cascade = CascadeType.ALL)
	private  BankAccount bankAccount;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL , mappedBy="wallet")
	private List<Transaction> transactions = new ArrayList<>();
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<BillPayment> billpayment = new ArrayList<>();
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "wallet")
	@JsonIgnore
	private List<BeneficiaryDetails> bd = new ArrayList<>();


	public Integer getWalletId() {
		return walletId;
	}


	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}


	public Integer getBalance() {
		return balance;
	}


	public void setBalance(Integer balance) {
		this.balance = balance;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public BankAccount getBankAccount() {
		return bankAccount;
	}


	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}


	public List<BillPayment> getBillpayment() {
		return billpayment;
	}


	public void setBillpayment(List<BillPayment> billpayment) {
		this.billpayment = billpayment;
	}


	public List<BeneficiaryDetails> getBd() {
		return bd;
	}


	public void setBd(List<BeneficiaryDetails> bd) {
		this.bd = bd;
	}


	public Wallet(Integer walletId, Integer balance, Customer customer, BankAccount bankAccount,
			List<Transaction> transactions, List<BillPayment> billpayment, List<BeneficiaryDetails> bd) {
		super();
		this.walletId = walletId;
		this.balance = balance;
		this.customer = customer;
		this.bankAccount = bankAccount;
		this.transactions = transactions;
		this.billpayment = billpayment;
		this.bd = bd;
	}

	public Wallet() {
		
	}

	
	
	
	
}
