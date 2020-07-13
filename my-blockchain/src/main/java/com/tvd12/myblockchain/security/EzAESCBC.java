package com.tvd12.myblockchain.security;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EzAESCBC {
	
	private final static int IV_SIZE = 16;
	private final static EzAESCBC INSTANCE = new EzAESCBC();
	
	private EzAESCBC() {}
	
	public static EzAESCBC getInstance() {
		return INSTANCE;
	}

	public byte[] encrypt(String key, byte[] data) throws Exception {

		// Generating IV.
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        // Encrypt.
        Cipher cipher = getCipher(key, iv, Cipher.ENCRYPT_MODE);
        byte[] encryptedBytes = cipher.doFinal(data);

        // Combine IV and encrypted part.
        byte[] encryptedData = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, encryptedData, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, encryptedData, iv.length, encryptedBytes.length);

        return encryptedData;
    }

    public byte[] decrypt(String key, byte[] encryptedData) throws Exception {

        // Extract IV.
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(encryptedData, 0, iv, 0, iv.length);

        // Extract encrypted part.
        int encryptedSize = encryptedData.length - iv.length;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedData, iv.length, encryptedBytes, 0, encryptedSize);

        // Decrypt.
        Cipher cipher = getCipher(key, iv, Cipher.DECRYPT_MODE);
        return cipher.doFinal(encryptedBytes);
    }
    
    private Cipher getCipher(String key, byte[] iv, int mode) throws Exception {
    	SecretKeySpec secretKeySpec = getSecretKeySpec(key);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(mode, secretKeySpec, ivParameterSpec);
        return cipher;
    }
    
    private SecretKeySpec getSecretKeySpec(String key) throws Exception {
    	byte[] keyBytes = hashKey(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        return secretKeySpec;
    }
    
    private byte[] hashKey(String key) throws Exception {
    	MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(key.getBytes("UTF-8"));
        return digest.digest();
    }
	
}
