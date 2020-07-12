package com.tvd12.my.blockchain.testing;

import com.tvd12.my.blockchain.EzBlockchain;
import com.tvd12.my.blockchain.EzMineSchedule;

public class EzTest002 {

	public static void main(String[] args) {
		EzBlockchain.getInstance();
		EzMineSchedule mineSchedule = EzMineSchedule.getInstance();
		mineSchedule.start();
	}
	
}
