package com.mpay.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpay.exceptions.LoginException;
import com.mpay.model.CurrentUserSession;
import com.mpay.model.Customer;
import com.mpay.model.LoginDTO;
import com.mpay.repositories.CustomerRepo;
import com.mpay.repositories.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private CustomerRepo cRepo;
	@Autowired
	private SessionRepo sRepo;

	@Override
	public String LoginToAccount(LoginDTO loginDTO) throws LoginException {
		Customer existingCustomer = cRepo.findByMobile(loginDTO.getMobile());
		if (existingCustomer == null) {
			throw new LoginException("please enter valid mobile number");

		}
		Optional<CurrentUserSession> validCustomerSessionOpt = sRepo.findById(existingCustomer.getMobile());
		if (validCustomerSessionOpt.isPresent()) {
			throw new LoginException("User already logged in with this number");
		}
		if (existingCustomer.getPassword().equals(loginDTO.getPassword())) {
			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession();
			currentUserSession.setKey(key);
			currentUserSession.setMobile(existingCustomer.getMobile());
			sRepo.save(currentUserSession);
			return key;
		} else {
			throw new LoginException("please enter valid password");
		}
	}

	@Override
	public String LogOutFromAccount(String Key) throws LoginException {
		CurrentUserSession currentUserSession = sRepo.findByKey(Key);
		if (currentUserSession == null) {
			throw new LoginException("user not logged in with this number");
		} else {
			sRepo.delete(currentUserSession);
			return "logged out !";
		}
	}

}
