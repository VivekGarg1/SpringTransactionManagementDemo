package com.home.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.home.model.Account;

public class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Account account=new Account();
		account.setAccountNo(resultSet.getLong("account_no"));
		account.setAccountHolderName(resultSet.getString("account_holder_name"));
		account.setAccountBalance(resultSet.getDouble("account_balance"));
		account.setAccountType(resultSet.getString("account_type"));
		return account;
	}

}
