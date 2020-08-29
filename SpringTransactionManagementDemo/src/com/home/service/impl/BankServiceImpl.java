package com.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.home.dao.BankDao;
import com.home.exception.InsufficientAccountBalance;
import com.home.model.Account;
import com.home.service.BankService;

@Service("bankService")
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao bankDao;

	/*@Autowired
	private TransactionTemplate transactionTemplate;*/

	// Without Transaction handling if deposit return Exception then withdraw success but deposit fail so loss the money
	/*@Override
	public void transferFund(Account fromAccount, Account toAccount, Double amount) {
		try {
			bankDao.withdraw(fromAccount, toAccount, amount);
			bankDao.deposit(fromAccount, toAccount, amount);
		} catch (InsufficientAccountBalance e) {
			System.out.println(e.getMessage());
		}
	}*/

	// Using Transaction template

	/*@Override
	public void transferFund(Account fromAccount, Account toAccount, Double amount)  {
		transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
			public Void doInTransaction(TransactionStatus transactionStatus) {
            	try {
					bankDao.withdraw(fromAccount, toAccount, amount);
					bankDao.deposit(fromAccount, toAccount, amount);
				} catch (InsufficientAccountBalance|RuntimeException e) {
					System.out.println("Exception is: "+e.getMessage());
				}
				return null;
			}
		});
	}*/
	
	//Using @Transactional
	/*@Override
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED, rollbackFor = Exception.class,readOnly = false,timeout = 100)
	public void transferFund(Account fromAccount, Account toAccount, Double amount) throws InsufficientAccountBalance{
			bankDao.withdraw(fromAccount, toAccount, amount);
			bankDao.deposit(fromAccount, toAccount, amount);
	}*/
	
	//Using aop
	@Override
	public void transferFund(Account fromAccount, Account toAccount, Double amount) throws InsufficientAccountBalance{
		bankDao.withdraw(fromAccount, toAccount, amount);
		bankDao.deposit(fromAccount, toAccount, amount);
}
}
