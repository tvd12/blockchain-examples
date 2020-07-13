package com.tvd12.myblockchain.security;

import com.tvd12.ezyfox.sercurity.EzyBase64;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EzKeyPair {

	public final byte[] privateKey;
	public final byte[] publicKey;
	
	public final String base64PublicKey() {
		return EzyBase64.encode2utf(publicKey);
	}
	
}
