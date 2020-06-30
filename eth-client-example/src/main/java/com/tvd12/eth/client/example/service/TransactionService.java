package com.tvd12.eth.client.example.service;

import java.util.Map;

import com.tvd12.eth.client.example.data.AccountData;

public interface TransactionService {

	Map<String, AccountData> transferMoney(String fromPassword,
			String fromWalletFile,
			String from, String to, long value) throws Exception;
	
}
