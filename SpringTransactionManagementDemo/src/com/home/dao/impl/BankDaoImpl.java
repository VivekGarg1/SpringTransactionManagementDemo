package com.home.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.home.dao.BankDao;
import com.home.exception.InsufficientAccountBalance;
import com.home.mapper.AccountRowMapper;
import com.home.model.Account;

@Repository
public class BankDaoImpl implements BankDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	@Override
	public void withdraw(Account fromAccount, Account toAccount, Double amount) throws InsufficientAccountBalance {
		Account accountFromDB = getAccountInfoFromDB(fromAccount.getAccountNo());
		if (accountFromDB.getAccountBalance() < amount)
			throw new InsufficientAccountBalance("Insufficient account balance");
		else {
			Double accountBalance = accountFromDB.getAccountBalance() - amount;
			String sql = "update bank_table set account_balance=? where account_no=?";
			int update = jdbctemplate.update(sql, accountBalance, fromAccount.getAccountNo());
			if (update > 0) {
				System.out.println("Amount Rs. " + amount + " is transfered from Account No: "
						+ fromAccount.getAccountNo() + " to Acount No: " + toAccount.getAccountNo());
			}
		}
	}

	@Override
	public void deposit(Account fromAccount, Account toAccount, Double amount) {
		Account accountFromDB = getAccountInfoFromDB(toAccount.getAccountNo());
		Double accountBalance = accountFromDB.getAccountBalance() + amount;
		String sql = "update bank_table set account_balance=? where account_no=?";
		int update = jdbctemplate.update(sql, accountBalance, toAccount.getAccountNo());
		if (update > 0) {
			System.out.println("Amount Rs. " + amount + " is deposited in Account No: " + toAccount.getAccountNo());
		}
		//throw new RuntimeException();
	}

	private Account getAccountInfoFromDB(Long accountNo) {
		String sql = "select * from bank_table where account_no=?";
		Account account = jdbctemplate.queryForObject(sql, new AccountRowMapper(), accountNo);
		return account;
	}

}
