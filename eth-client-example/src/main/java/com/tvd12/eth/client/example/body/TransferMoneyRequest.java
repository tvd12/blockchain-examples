package com.tvd12.eth.client.example.body;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferMoneyRequest {

	private String fromPassword;
	private String fromWalletFile;
	private String from;
	private String to;
	private long value;
	
}
