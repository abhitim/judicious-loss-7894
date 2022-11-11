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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer walletId;
	private Integer balance;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Customer customer;
	 
	@OneToMany(cascade = CascadeType.ALL)
	private  List<BeneficiaryDetails> beneficiaryDetails =new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL , mappedBy="wallet")
	private List<Transaction> transactions = new ArrayList<>();
	
	


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




	public List<Transaction> getTransactions() {
		return transactions;
	}



	public Wallet() {
		
	}

	
	
	
	
}
