package com.masai.model;

import lombok.Data;

@Data
public class SigninDto {

	private String mobileNo;
	private String password;
	//private String role;
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SigninDto(String mobileNo, String password) {
		super();
		this.mobileNo = mobileNo;
		this.password = password;
	}
	public SigninDto() {
		
	}
	
}
