package com.tvd12.eth.client.example.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class EthWeb3jConfig {

	@Bean
	public Web3j web3j() {
		Web3j web3j = Web3j.build(new HttpService(
                "http://localhost:8545")); 
		return web3j;
	}
	
}
