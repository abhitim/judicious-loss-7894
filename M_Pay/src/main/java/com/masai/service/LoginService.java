package com.masai.service;

import com.masai.Exceptions.LoginException;

import com.masai.model.SigninDto;

public interface LoginService {
	
	public String logIntoAccount(SigninDto dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
