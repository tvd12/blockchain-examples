package com.tvd12.my.blockchain;

import lombok.Getter;

@Getter
public class EzTransaction implements EzHashable {

	private String id;
	private String hash;
	private String from;
	private String to;
	private long value;
	private String input;
	private long timestamp;
	private String sign;
	private String publicKey;
	private Status status;
	
	public static enum Status {
		PENDING,
		VERIFIED,
		FAILED
	}
	
	public void verify() {
		// TODO: 
	}
	
}
