package com.home.dao;

import com.home.exception.InsufficientAccountBalance;
import com.home.model.Account;

public interface BankDao {
	
	public abstract void withdraw(Account fromAccount,Account toAccount,Double amount) throws InsufficientAccountBalance;
	public abstract void deposit(Account fromAccount,Account toAccount,Double amount);

}
