package com.tvd12.blockchain.example3;

import java.util.LinkedList;

public class BlockChain {

	private long size;
	private LinkedList<Block> blocks = new LinkedList<>();
	
	public void addBlock(Block block) {
		synchronized (blocks) {
			this.blocks.add(block);
			this.size ++;
		}
	}
	
	public Block getLastBlock() {
		synchronized (blocks) {
			if(blocks.isEmpty())
				throw new IllegalArgumentException("block chain is empty");
			return blocks.getLast();
		}
	}
	
	public long size() {
		return size;	
	}
	
}
