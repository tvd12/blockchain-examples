package com.tvd12.my.blockchain.store;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.tvd12.ezyfox.io.EzyByteBuffers;
import com.tvd12.myblockchain.security.EzKeyPair;

public final class EzAccountStore {

	private static final EzAccountStore INSTANCE = new EzAccountStore();
	
	private EzAccountStore() {}
	
	public static EzAccountStore getInstance() {
		return INSTANCE;
	}
	
	public void store(String address, EzKeyPair keyPair) throws Exception {
		Path filePath = Paths.get("data/accounts/" + address);
		Files.createDirectories(filePath.getParent());
		ByteBuffer buffer = ByteBuffer.allocate(
				2 
				+ keyPair.getPrivateKey().length
				+ keyPair.getPublicKey().length);
		buffer.putShort((short)keyPair.getPrivateKey().length);
		buffer.put(keyPair.getPrivateKey());
		buffer.put(keyPair.getPublicKey());
		Files.write(filePath, buffer.array());
	}
	
	public EzKeyPair getKeyPair(String address) throws Exception {
		Path filePath = Paths.get("data/accounts/" + address);
		if(!Files.exists(filePath))
			throw new IllegalArgumentException("address not found");
		byte[] bytes = Files.readAllBytes(filePath);
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		int privateKeySize = buffer.getShort();
		byte[] privateKey = EzyByteBuffers.getBytes(buffer, privateKeySize);
		byte[] publicKey = EzyByteBuffers.getBytes(buffer);
		return new EzKeyPair(privateKey, publicKey);
	}
	
}
