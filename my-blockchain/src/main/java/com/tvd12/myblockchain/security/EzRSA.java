package com.tvd12.myblockchain.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class EzRSA {
	
	private final static EzRSA INSTANCE = new EzRSA();
	
	private EzRSA() {}
	
	public static EzRSA getInstance() {
		return INSTANCE;
	}

	public KeyPair generateKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		return keyPair;
	}
	
}
