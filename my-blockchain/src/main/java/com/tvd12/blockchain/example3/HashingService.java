package com.tvd12.blockchain.example3;

import com.tvd12.blockchain.security.SHA256HashBuilder;

public class HashingService {

	public String hash(Object... objects) {
		SHA256HashBuilder hashBuilder = new SHA256HashBuilder();
		for(Object obj : objects)
			hashBuilder.append(obj);
		return hashBuilder.build();
	}
	
}
