package com.tvd12.blockchain.security;

import com.tvd12.ezyfox.sercurity.EzySHA256;

public class SHA256HashBuilder extends HashBuilder {

	@Override
	protected String hash(String chain) {
		return EzySHA256.cryptUtfToLowercase(chain);
	}
	
}
