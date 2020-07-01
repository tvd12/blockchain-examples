package com.tvd12.blockchain.example3;

import java.util.Date;

import lombok.Getter;

@Getter
public class BlockContent {
	
	private Date timestamp;
	private String data;
	
	public BlockContent(Date timestamp, String data) {
		this.timestamp = timestamp;
		this.data = data;
	}
}
