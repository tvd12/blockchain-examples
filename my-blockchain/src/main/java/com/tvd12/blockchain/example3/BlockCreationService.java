package com.tvd12.blockchain.example3;

import java.util.Date;

public class BlockCreationService {

	private final HashingService hashingService;
	
	public BlockCreationService(HashingService hashingService) {
		this.hashingService = hashingService;
	}
	
	public Block createFirstBlock() {
		long index = 0L;
		Date timestamp = new Date();
		String data = "";
		String previousHash = "";
		int nonce = 0;
		String hash = hashingService.hash(index, timestamp, data, previousHash, nonce);
		return new Block(index, timestamp, data, previousHash, hash, nonce);
	}
	
}
