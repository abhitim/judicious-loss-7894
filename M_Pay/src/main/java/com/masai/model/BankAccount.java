package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class BankAccount {

	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accountNo;
	
//	@NotNull
//	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}")
	private String ifscCode;
	
	@NotNull
	private String bankName;
	
	@NotNull
	@Min(0)
	private double balance;
	
	
	
	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "bankAccount")
	private Wallet wallet;



	public Integer getAccountNo() {
		return accountNo;
	}



	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}



	public String getIfscCode() {
		return ifscCode;
	}



	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}



	public String getBankName() {
		return bankName;
	}



	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	public Wallet getWallet() {
		return wallet;
	}



	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}



	public BankAccount(Integer accountNo, String ifscCode, @NotNull String bankName, @NotNull @Min(0) double balance,
			Wallet wallet) {
		super();
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.balance = balance;
		this.wallet = wallet;
	}



	public BankAccount() {
		super();
	}
	
	
	
}
