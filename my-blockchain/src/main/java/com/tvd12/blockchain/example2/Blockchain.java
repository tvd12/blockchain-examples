package com.tvd12.blockchain.example2;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Blockchain {

	private int degree = 5;
	private List<Block> blocks = new ArrayList<>();
	private Queue<Transaction> transactionQueue = new LinkedList<>();
	private long rewardForMiner = 100;
	
	public void init() {
		this.blocks.add(new Block(1, new Date(), "0", transactionQueue));
	}
	
	public Block getLastBlock() {
		synchronized (blocks) {
			return blocks.get(size() - 1);
		}
	}
	
	public Block mineBlock(String minerWalletAddress) {
		synchronized (transactionQueue) {
			if(transactionQueue.isEmpty())
				throw new IllegalArgumentException("has no transaction");
			int size = blocks.size();
			Block lastBlock = blocks.get(size - 1);
			Block block = new Block(size, new Date(), lastBlock.getHash(), transactionQueue);
			block.mine(degree);
			blocks.add(block);
			transactionQueue.add(new Transaction("system", minerWalletAddress, rewardForMiner));
			return block;
		}
	}
	
	public void addTransaction(Transaction transaction) {
		synchronized (transactionQueue) {
			transactionQueue.add(transaction);
		}
	}
	
	public long getMoney(String walletAddress) {
		long money = 0;
		for(Block block : blocks) {
			for(Transaction transaction : block.getTransactions()) {
				if(transaction.getFrom().equals(walletAddress))
					money -= transaction.getValue();
				else if(transaction.getTo().equals(walletAddress))
					money += transaction.getValue();
			}
		}
		return money;
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
