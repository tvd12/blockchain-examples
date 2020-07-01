package com.tvd12.blockchain.example3;

import java.util.Date;

import com.tvd12.ezyfox.io.EzyPrints;

public class MiningService {

	private HashingService hashingService;
	
	public MiningService(HashingService hashingService) {
		this.hashingService = hashingService;
	}
	
	public MiningResult mine(
			int index,
			Date timestamp, 
			String data, String previousHash, int degree) {
		int nonce = 0;
		String hash = "";
		String x0 = EzyPrints.insertBegin("", "0", degree);
		do {
			hash = hashingService.hash(index, timestamp, data, previousHash, nonce);
			if(x0.equals(hash.substring(0, degree)))
				return new MiningResult(nonce, hash);
			nonce ++;
		}
		while(true);
	}
	
}
