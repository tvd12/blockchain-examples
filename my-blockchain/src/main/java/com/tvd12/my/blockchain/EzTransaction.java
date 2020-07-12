package com.tvd12.my.blockchain;

import java.util.Map;

import lombok.Getter;

@Getter
public class EzTransaction {

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
	
	public EzTransaction(Map<String, Object> map) {
		this.id = (String) map.get("id");
		this.hash = (String) map.get("hash");
		this.from = (String) map.get("from");
		this.value = ((Number) map.get("to")).longValue();
		this.input = (String) map.get("input");
		this.timestamp = ((Number) map.get("timestamp")).longValue();
		this.sign = (String) map.get("sign");
		this.publicKey = (String) map.get("publicKey");
		this.status = Status.valueOf((String) map.get("status"));
	}
	
	public void verify() {
		// TODO: 
	}
	
	public static enum Status {
		PENDING,
		VERIFIED,
		FAILED
	}
	
}
