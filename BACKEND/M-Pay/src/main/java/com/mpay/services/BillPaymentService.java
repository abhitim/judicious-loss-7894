package com.mpay.services;

import java.util.List;

import com.mpay.exceptions.InsufficientFundException;
import com.mpay.exceptions.CredentialsNotValidException;
import com.mpay.exceptions.WalletException;
import com.mpay.model.BillPayment;

public interface BillPaymentService {

	public BillPayment addBillPayment(BillPayment bill, String key)
			throws CredentialsNotValidException, InsufficientFundException;

	public List<BillPayment> viewAllBillPayments(String key) throws CredentialsNotValidException, WalletException;

}
