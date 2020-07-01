package com.tvd12.blockchain.example2;

import lombok.Getter;

@Getter
public class Transaction {

	private String from;
	private String to;
	private long value;
	
	public Transaction(String from, String to, long value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}
	
}
