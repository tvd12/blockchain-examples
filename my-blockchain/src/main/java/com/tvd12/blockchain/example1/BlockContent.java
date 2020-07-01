package com.tvd12.blockchain.example1;

import java.util.Date;

import lombok.Getter;

@Getter
public class BlockContent {

	private final Date timestamp;
	private final String data;
	
	public BlockContent(Date timestamp, String data) {
		this.timestamp = timestamp;
		this.data = data;
	}
	
}
