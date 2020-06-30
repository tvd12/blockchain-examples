package com.tvd12.eth.client.example.service.impl;

import java.io.File;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import com.tvd12.eth.client.example.data.AccountData;
import com.tvd12.eth.client.example.service.AbstractWeb3jService;
import com.tvd12.eth.client.example.service.AccountService;

import lombok.Setter;

@Setter
@Service("accountService")
public class AccountServiceImpl extends AbstractWeb3jService implements AccountService {

	@Value("${keystore.folder.path}")
	private String keystoreFolderPath;
	
	@Override
	public String createAccount(String password) throws Exception {
		String walletFileName = WalletUtils.generateFullNewWalletFile(password, new File(keystoreFolderPath));
		String[] fetchAddress= walletFileName.split("--");
		String accountAddress = fetchAddress[fetchAddress.length-1].split("\\.")[0];
		return accountAddress;
	}
	
	@Override
	public Map<String, AccountData> getAllAccounts() throws Exception {
		Request<?, EthAccounts> request = web3j.ethAccounts();
		EthAccounts response = request.send();
		List<String> addresses = response.getAccounts();
		Map<String, AccountData> answer = new HashMap<>();
		Map<String, BigInteger> accountValues = getAccountValues(addresses);
		for(String address : addresses) {
			answer.put(address, new AccountData(address, accountValues.get(address)));
		}
		return answer;
	}
	
	public Map<String, BigInteger> getAccountValues(Collection<String> addresses) throws Exception {
		Map<String, CompletableFuture<EthGetBalance>> tasks = new HashMap<>();
		for(String address : addresses) {
			Request<?, EthGetBalance> getBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST);
			CompletableFuture<EthGetBalance> task = getBalance.sendAsync();
			tasks.put(address, task);
		}
		Map<String, BigInteger> answer = new HashMap<>();
		for(String address : addresses) {
			BigInteger balance = tasks.get(address).get().getBalance();
			answer.put(address, balance);
		}
		return answer;
	}

}
