package com.tvd12.blockchain.testing;

import com.tvd12.blockchain.example2.Blockchain;
import com.tvd12.blockchain.example2.Transaction;

public class Test002 {

	public static void main(String[] args) {
		Blockchain blockchain = new Blockchain();
		blockchain.init();
		blockchain.addTransaction(new Transaction("A", "B", 100));
		blockchain.addTransaction(new Transaction("B", "A", 100));
		blockchain.mineBlock("miner");
		blockchain.mineBlock("miner");
		System.out.println(blockchain.getMoney("miner"));
	}
	
}
