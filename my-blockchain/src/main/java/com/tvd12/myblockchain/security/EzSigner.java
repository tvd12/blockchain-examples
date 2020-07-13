package com.tvd12.myblockchain.security;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.tvd12.ezyfox.sercurity.EzyBase64;

public final class EzSigner {

	private final static EzSigner INSTANCE = new EzSigner();
	
	private EzSigner() {}
	
	public static EzSigner getInstance() {
		return INSTANCE;
	}
	
	public String sign(byte[] privateKey, String hash) throws Exception {
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(getPrivate(privateKey));
		signature.update(hash.getBytes());
		return EzyBase64.encode2utf(signature.sign());
	}
	
	public boolean verify(byte[] publicKey, String hash, String sign) throws Exception {
		Signature sig = Signature.getInstance("SHA1withRSA");
		sig.initVerify(getPublic(publicKey));
		sig.update(hash.getBytes());
		return sig.verify(EzyBase64.decode(sign));
	}
	
	private PrivateKey getPrivate(byte[] keyBytes) throws Exception {
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}
	
	public PublicKey getPublic(byte[] keyBytes) throws Exception {
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}
	
}
