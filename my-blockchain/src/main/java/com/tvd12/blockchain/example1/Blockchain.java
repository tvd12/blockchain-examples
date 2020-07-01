package com.tvd12.blockchain.example1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blockchain {

	private int degree = 5;
	private List<Block> blocks = new ArrayList<>();
	
	public void init() {
		this.blocks.add(new Block(1, new Date(), "0", "first block"));
	}
	
	public Block getLastBlock() {
		synchronized (blocks) {
			return blocks.get(size() - 1);
		}
	}
	
	public Block newAndAddBlock(BlockContent content) {
		synchronized (blocks) {
			int size = blocks.size();
			Block lastBlock = blocks.get(size - 1);
			Block newBlock = new Block(size, content.getTimestamp(), lastBlock.getHash(), content.getData());
			newBlock.mine(degree);
			blocks.add(newBlock);
			return newBlock;
		}
	}
	
	public boolean validate() {
		for(Block block : blocks) {
			if(!block.getHash().equals(block.hash()))
				return false;
		}
		return true;
	}
	
	public int size() {
		synchronized (blocks) {
			return blocks.size();	
		}
	}
	
}
