package com.tvd12.blockchain.testing;

import java.util.Date;

import com.tvd12.blockchain.example1.Block;
import com.tvd12.blockchain.example1.BlockContent;
import com.tvd12.blockchain.example1.Blockchain;

public class Test001 {

	public static void main(String[] args) {
		Blockchain blockchain = new Blockchain();
		blockchain.init();
		Block newBlock1 = blockchain.newAndAddBlock(new BlockContent(new Date(), "hello world 1"));
		System.out.println("newBlock1.hash: " + newBlock1.getHash());
		Block newBlock2 = blockchain.newAndAddBlock(new BlockContent(new Date(), "hello world 1"));
		System.out.println("newBlock2.hash: " + newBlock2.getHash());
	}
	
}
