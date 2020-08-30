package com.tvd12.my.blockchain;

public class EzWalletTool {

	public static void main(String[] args) {
		EzBlockchain.getInstance();
		EzMineSchedule mineSchedule = EzMineSchedule.getInstance();
		mineSchedule.start();
	}
	
}
