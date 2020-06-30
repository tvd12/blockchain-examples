package com.tvd12.eth.client.example.data;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import lombok.Getter;

@Getter
public class AccountData {

	private String address;
	private BigInteger wei;
	private BigDecimal eth;
	
	public AccountData(String address, BigInteger wei) {
		this.address = address;
		this.wei = wei;
		this.eth = Convert.fromWei(wei.toString(), Unit.ETHER); 
	}
	
}
