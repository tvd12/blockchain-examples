package com.tvd12.eth.client.example.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

import com.tvd12.eth.client.example.data.AccountData;

public interface AccountService {

	String createAccount(String password) throws Exception;
	
	Map<String, AccountData> getAllAccounts() throws Exception;
	
	Map<String, BigInteger> getAccountValues(Collection<String> addresses) throws Exception;
	
}
