package com.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.dao.BankDao;
import com.home.exception.InsufficientAccountBalance;
import com.home.model.Account;
import com.home.service.BankService;

@Service("bankService")
public class BankServiceImpl implements BankService{
	
	@Autowired
	private BankDao bankDao;

	@Override
	public void transferFund(Account fromAccount, Account toAccount, Double amount)  {
		try {
			bankDao.withdraw(fromAccount, toAccount, amount);
			bankDao.deposite(fromAccount, toAccount, amount);
		} catch (InsufficientAccountBalance e) {
			System.out.println(e.getMessage());
		}
	}

}
