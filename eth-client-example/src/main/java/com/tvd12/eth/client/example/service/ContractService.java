package com.tvd12.eth.client.example.service;

import com.tvd12.eth.client.example.AmazingToken;

public interface ContractService {

	AmazingToken loadAmazingToken() throws Exception;
	
	AmazingToken deployAmazingToken() throws Exception;
	
	void transfer(String to, long value) throws Exception;
	
}