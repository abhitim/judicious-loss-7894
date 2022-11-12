package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BeneficiaryDetails {
	public BeneficiaryDetails(){
		
	}
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Id
	@Pattern(regexp = "[0-9]{10}", message = "Number entered is not an valid number")
	private String mobileNumber;
	
	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9]{1,100}", message = "Name can't be Empty")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
	
}