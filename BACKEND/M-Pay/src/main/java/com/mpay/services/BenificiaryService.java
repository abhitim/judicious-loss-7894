package com.mpay.services;

import java.util.List;

import com.mpay.exceptions.BeneficiaryNotFoundException;
import com.mpay.exceptions.CustomerException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.Beneficiary;

public interface BenificiaryService {

	public Beneficiary addBeneficiary(Beneficiary bd, String key)
			throws CustomerException, WalletException, CredentialsNotValidException;

	public Beneficiary deleteBeneficiary(Beneficiary bd, String key)
			throws BeneficiaryNotFoundException, WalletException, CredentialsNotValidException;

	public Beneficiary viewBeneficiary(String mobile, String key)
			throws BeneficiaryNotFoundException, CredentialsNotValidException;

	public List<Beneficiary> viewAllBeneficiary(String key)
			throws BeneficiaryNotFoundException, CredentialsNotValidException;

}
