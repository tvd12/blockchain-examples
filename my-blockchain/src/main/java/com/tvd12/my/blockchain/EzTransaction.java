package com.tvd12.my.blockchain;

import com.tvd12.myblockchain.security.EzSHA256HashBuilder;
import com.tvd12.myblockchain.security.EzSigner;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EzTransaction {

	private final String id;
	private final String from;
	private final String to;
	private final long value;
	private final String input;
	private final long timestamp;
	private final String publicKey;
	private String hash;
	private String sign;
	private Status status;
	
	public EzTransaction(
			String id,
			String from,
			String to,
			long value,
			String input,
			String publicKey) {
		this(
				id, 
				"", 
				from, 
				to, 
				value, 
				input, 
				System.currentTimeMillis(), 
				"", 
				publicKey, 
				Status.VERIFIED
		);
		this.hash = hash();
	}
	
	public EzTransaction(
			String id,
			String hash,
			String from,
			String to,
			long value,
			String input,
			long timestamp,
			String sign,
			String publicKey,
			Status status) {
		this.id = id;
		this.hash = hash;
		this.from = from;
		this.to = to;
		this.value = value;
		this.input = input;
		this.timestamp = timestamp;
		this.sign = sign;
		this.publicKey = publicKey;
		this.status = status;
	}
	
	public void verify() {
		// TODO: 
	}
	
	public String sign(byte[] privateKey) throws Exception {
		EzSigner signer = EzSigner.getInstance();
		this.sign = signer.sign(privateKey, hash);
		return sign;
	}
	
	public String hash() {
		return new EzSHA256HashBuilder()
				.append(id)
				.append(hash)
				.append(from)
				.append(to)
				.append(value)
				.append(input)
				.append(timestamp)
				.append(sign)
				.append(publicKey)
				.build();
	}
	
	public static enum Status {
		PENDING,
		VERIFIED,
		FAILED
	}
	
}
