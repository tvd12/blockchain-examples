package com.tvd12.my.blockchain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class EzBlockchain {

	@Getter
	private int degree;
	@Getter
	private long rewardForMiner;
	@Getter
	private final int maxFreeBlocks;
	private final List<EzBlock> blocks;
	private final static EzBlockchain INSTANCE = new EzBlockchain(); 
	
	private EzBlockchain() {
		this.degree = 5;
		this.maxFreeBlocks = 100;
		this.rewardForMiner = 100;
		this.blocks = new ArrayList<>();
		this.load();
	}
	
	public static EzBlockchain getInstance() {
		return INSTANCE;
	}
	
	public void addBlock(EzBlock block) {
		synchronized (blocks) {
			blocks.add(block);
		}
	}
	
	public EzBlock getLastBlock() {
		synchronized (blocks) {
			return blocks.get(size() - 1);
		}
	}
	
	public long getMoney(String address) {
		long money = 0;
		for(EzBlock block : blocks) {
			for(EzTransaction transaction : block.getTransactions()) {
				if(transaction.getFrom().equals(address))
					money -= transaction.getValue();
				else if(transaction.getTo().equals(address))
					money += transaction.getValue();
			}
		}
		return money;
	}
	
	public void validate() {
		synchronized (this) {
			if(blocks.isEmpty())
				return;
			EzBlock preBlock = blocks.get(0);
			for(int i = 1 ; i < blocks.size() ; ++i) {
				EzBlock block = blocks.get(i);
				if(block.getHash().equals(preBlock.getHash()))
					block.verify();
				else
					throw new IllegalStateException("invalid blockchain");
			}
		}
	}
	
	public int size() {
		synchronized (blocks) {
			return blocks.size();	
		}
	}
	
	private void load() {
		if(blocks.isEmpty()) {
			this.blocks.add(new EzGenesisBlock());
		}
	}
	
}
