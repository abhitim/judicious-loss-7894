package com.masai.model;

 
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer customerId;
	private String name;
	private String mobileNo;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;

	public Integer getId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String setCustomerPassword() {
		return mobileNo;
	}

	public void setMobile(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMobile() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCustomerPassword() {
		return password;
	}

	public void setCustomerPassword(String password) {
		this.password = password;
	}

	public Wallet getWallets() {
		return wallet;
	}

	public void setWallets(Wallet wallet) {
		this.wallet = wallet;
	}

	public Customer(Integer customerId, String name, String mobileNo, String password, Wallet wallet) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.password = password;
		this.wallet = wallet;
	}
	public Customer() {
		
	}
	
	
	
	

}
