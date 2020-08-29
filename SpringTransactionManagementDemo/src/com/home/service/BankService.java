package com.home.service;

import com.home.model.Account;

public interface BankService {
	
	public abstract void transferFund(Account fromAccount,Account toAccount,Double amount);

}
