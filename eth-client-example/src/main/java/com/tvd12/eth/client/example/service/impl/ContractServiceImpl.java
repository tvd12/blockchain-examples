package com.tvd12.eth.client.example.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import com.tvd12.eth.client.example.AmazingToken;
import com.tvd12.eth.client.example.service.AbstractWeb3jService;
import com.tvd12.eth.client.example.service.ContractService;

import lombok.Setter;

@Setter
@Service("contractService")
public class ContractServiceImpl extends AbstractWeb3jService implements ContractService {

	@Value("${keystore.folder.path}")
	private String keystoreFolderPath;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public AmazingToken loadAmazingToken() throws Exception {
		Credentials credentials =
                WalletUtils.loadCredentials(
                		"123abc",
                		keystoreFolderPath + "UTC--2018-08-08T12-50-55.413102254Z--2b7afd366f23f4a426d0c9a584a97976e93f3cbb");
		return AmazingToken.load("0x981fd5ecf0fb148f810922cab24fe93de470436e", web3j, credentials, BigInteger.valueOf(100000), BigInteger.valueOf(2000000));
	}
	
	@Override
	public AmazingToken deployAmazingToken() throws Exception {
		Credentials credentials =
                WalletUtils.loadCredentials(
                		"123abc",
                		keystoreFolderPath + "UTC--2018-08-08T12-50-55.413102254Z--2b7afd366f23f4a426d0c9a584a97976e93f3cbb");
		logger.info("deploying amazing token");
		AmazingToken contract = AmazingToken
				.deploy(web3j, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT)
				.send();
		String tokenAddress = contract.getContractAddress();
		logger.info("tokenAddress: {}", tokenAddress);
		return contract;
	}
	
	@Override
	public void transfer(String to, long value) throws Exception {
		Credentials credentials =
                WalletUtils.loadCredentials(
                		"123abc",
                		keystoreFolderPath + "UTC--2018-08-08T12-50-55.413102254Z--2b7afd366f23f4a426d0c9a584a97976e93f3cbb");
		AmazingToken contract = AmazingToken.load("0x0f541ab9e31594b4367a5996839bda3991349061", web3j, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
		TransactionReceipt receipt = contract.transfer(to, Convert.toWei(BigDecimal.valueOf(value), Unit.ETHER).toBigInteger()).send();
		logger.info("receipt info: " + receipt.getBlockHash());
	}
	
}
