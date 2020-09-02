package com.tvd12.my.blockchain.testing;

import com.tvd12.my.blockchain.EzWallet;
import com.tvd12.my.blockchain.store.EzAccountStore;
import com.tvd12.myblockchain.security.EzKeyPair;

public class EzWalletToolTest {

	public static void main(String[] args) throws Exception {
		EzWallet wallet = EzWallet.getInstance();
		EzAccountStore accountStore = EzAccountStore.getInstance();
		
		String genesisAddress = wallet.createAccount("youngmonkeys");
		EzKeyPair genesisKeyPair = accountStore.getKeyPair(genesisAddress);
		System.out.println("genesisAddress: " + genesisAddress);
		System.out.println("genesisPublicKey: " + genesisKeyPair.base64PublicKey());
		System.out.println("genesisPrivateKey: " + genesisKeyPair.base64PrivateKey());
		
		String originAddress = wallet.createAccount("youngmonkeys");
		EzKeyPair originKeyPair = accountStore.getKeyPair(originAddress);
		System.out.println("originAddress: " + originAddress);
		System.out.println("originPublicKey: " + originKeyPair.base64PublicKey());
		System.out.println("originPrivateKey: " + originKeyPair.base64PrivateKey());
	}
	
}
