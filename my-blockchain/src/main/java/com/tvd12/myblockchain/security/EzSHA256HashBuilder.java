package com.tvd12.myblockchain.security;

import com.tvd12.ezyfox.sercurity.EzySHA256;

public class EzSHA256HashBuilder extends EzHashBuilder {

	@Override
	protected String hash(String chain) {
		return EzySHA256.cryptUtfToLowercase(chain);
	}
	
}
