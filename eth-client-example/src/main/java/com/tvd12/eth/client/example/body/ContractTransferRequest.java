package com.tvd12.eth.client.example.body;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractTransferRequest {

	private String to;
	private long value;
	
}
