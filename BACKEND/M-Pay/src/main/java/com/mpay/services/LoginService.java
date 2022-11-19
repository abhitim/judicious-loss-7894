package com.mpay.services;

import com.mpay.exceptions.LoginException;
import com.mpay.model.LoginDTO;

public interface LoginService {

	public String LoginToAccount(LoginDTO loginDTO)throws LoginException;
	public String LogOutFromAccount(String Key)throws LoginException;
	
}
