package com.tvd12.blockchain.example3;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Block {

	private long index;
	private Date timestamp;
	private String data;
	private String previousHash;
	private String hash;
	private int nonce;
	
	public String getHeaderString() {
		return new StringBuilder()
				.append(index)
				.append(timestamp)
				.append(data)
				.append(previousHash)
				.append(nonce)
				.toString();
	}
	
}
