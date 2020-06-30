package com.tvd12.eth.client.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.protocol.Web3j;

import lombok.Setter;

@Setter
public class AbstractWeb3jService {

	@Autowired
	protected Web3j web3j;
	
}
