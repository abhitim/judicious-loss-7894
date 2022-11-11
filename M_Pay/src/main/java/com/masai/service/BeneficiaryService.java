package com.masai.service;

import java.util.List;

import com.masai.Exceptions.BeneficiaryException;
import com.masai.model.BeneficiaryDetails;
import com.masai.model.Customer;

public interface BeneficiaryService {
	public BeneficiaryDetails addBeneficiary(BeneficiaryDetails beDetails)throws BeneficiaryException;
	public BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beDetails)throws BeneficiaryException;
	public BeneficiaryDetails veiwBeneficiary(String mobileNo)throws BeneficiaryException;
	public List<BeneficiaryDetails> veiwAllBeneficiary(Customer customer)throws BeneficiaryException;
}
