package com.tvd12.eth.client.example.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import com.tvd12.eth.client.example.data.AccountData;
import com.tvd12.eth.client.example.service.AbstractWeb3jService;
import com.tvd12.eth.client.example.service.TransactionService;

import lombok.Setter;

@Setter
@Service("transactionService")
public class TransactionServiceImpl extends AbstractWeb3jService implements TransactionService {

	@Value("${keystore.folder.path}")
	private String keystoreFolderPath;
	
	@Override
	public Map<String, AccountData> transferMoney(
			String fromPassword,
			String fromWalletFile,
			String from, String to, long value) throws Exception {
		
		Credentials credentials =
                WalletUtils.loadCredentials(
                		fromPassword,
                		keystoreFolderPath + fromWalletFile);
		@SuppressWarnings("unused")
		TransactionReceipt transferReceipt = Transfer.sendFunds(
				web3j, credentials, to, new BigDecimal(value), Convert.Unit.ETHER
		)
		.send();
		return new HashMap<>();
	}

}
