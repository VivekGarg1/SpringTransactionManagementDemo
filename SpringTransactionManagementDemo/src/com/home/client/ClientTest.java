package com.home.client;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.home.exception.InsufficientAccountBalance;
import com.home.model.Account;
import com.home.service.BankService;
import com.home.service.impl.BankServiceImpl;

public class ClientTest {

	public static void main(String[] args) throws InsufficientAccountBalance {
		AbstractApplicationContext  context=new ClassPathXmlApplicationContext("Beans.xml");
		BankService bankService = context.getBean("bankService", BankServiceImpl.class);
		
		Account fromAccount=new Account();
		fromAccount.setAccountNo(123456789L);
		
		Account toAccount=new Account();
		toAccount.setAccountNo(987654321L);
		
		bankService.transferFund(fromAccount, toAccount, 500.0);
		context.close();
	}
}
