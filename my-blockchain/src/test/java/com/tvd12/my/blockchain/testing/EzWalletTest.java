package com.tvd12.my.blockchain.testing;

import com.tvd12.my.blockchain.EzWallet;

public class EzWalletTest {

	public static void main(String[] args) {
		EzWallet wallet = EzWallet.getInstance();
//		System.out.println(wallet.createAccount("123456"));
		wallet.transer(
				"ad6fd0303a8cb59de65a266f67dc597f6e40732ee5b1388f9d1ee5d2b4f09e9b", 
				"123456", 
				"2de0d886014595b28d02928f1b99d6426ec5a69215706a71635322cfa1f739fc", 
				1000L);
	}
	
}
