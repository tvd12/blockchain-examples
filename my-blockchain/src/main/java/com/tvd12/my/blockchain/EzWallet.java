package com.tvd12.my.blockchain;

import java.security.KeyPair;
import java.util.UUID;

import com.tvd12.ezyfox.sercurity.EzySHA256;
import com.tvd12.my.blockchain.store.EzAccountStore;
import com.tvd12.myblockchain.security.EzAESCBC;
import com.tvd12.myblockchain.security.EzKeyPair;
import com.tvd12.myblockchain.security.EzRSA;

public final class EzWallet {
	
	private final AccountManager accountManager;
	private final static EzWallet INSTANCE = new EzWallet();
	
	private EzWallet() {
		this.accountManager = new AccountManager();
	}
	
	public static EzWallet getInstance() {
		return INSTANCE;
	}
	
	public String createAccount(String password) {
		try {
			return accountManager.create(password);
		}
		catch (Exception e) {
			throw new IllegalStateException("can't create account", e);
		}
	}
	
	public EzTransaction transfer(String from, String password, String to, long amount) {
		try {
			EzKeyPair keyPair = accountManager.getKeyPair(from, password);
			String transactionId = UUID.randomUUID().toString();
			EzTransaction transaction = new EzTransaction(
					transactionId, 
					from, 
					to, 
					amount, 
					"", 
					keyPair.base64PublicKey());
			transaction.sign(keyPair.getPrivateKey());
			EzTransactionPool.getInstance().add(transaction);
			return transaction;
		}
		catch (Exception e) {
			throw new IllegalArgumentException("transfer error", e);
		}
	}
	
	public static class AccountManager {
		
		public String create(String password) throws Exception {
			KeyPair keyPair = EzRSA.getInstance().generateKeyPair();
			byte[] privateKey = keyPair.getPrivate().getEncoded();
			byte[] publicKey = keyPair.getPublic().getEncoded();
			String accountAddress = EzySHA256.cryptUtfToLowercase(new String(publicKey));
			byte[] encryptedPrivateKey = EzAESCBC.getInstance().encrypt(password, privateKey);
			byte[] encryptedPublicKey = EzAESCBC.getInstance().encrypt(password, publicKey);
			EzKeyPair kp = new EzKeyPair(encryptedPrivateKey, encryptedPublicKey);
			EzAccountStore.getInstance().store(accountAddress, kp);
			return accountAddress;
		}
		
		public EzKeyPair getKeyPair(String address, String password) throws Exception {
			EzKeyPair encrypted = EzAccountStore.getInstance().getKeyPair(address);
			byte[] decryptedPrivateKey = EzAESCBC.getInstance().decrypt(password, encrypted.getPrivateKey());
			byte[] decryptedPublicKey = EzAESCBC.getInstance().decrypt(password, encrypted.getPublicKey());
			return new EzKeyPair(decryptedPrivateKey, decryptedPublicKey);
		}
		
	}
	
}
