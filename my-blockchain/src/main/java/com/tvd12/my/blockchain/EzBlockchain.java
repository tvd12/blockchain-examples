package com.tvd12.my.blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.tvd12.my.blockchain.store.EzFileStore;

import lombok.Getter;

public final class EzBlockchain {

	@Getter
	private int degree;
	@Getter
	private long rewardForMiner;
	@Getter
	private final int maxFreeBlocks;
	private final List<EzBlock> blocks;
	private final Store store;
	private final static EzBlockchain INSTANCE = new EzBlockchain(); 
	
	private EzBlockchain() {
		this.degree = 5;
		this.maxFreeBlocks = 100;
		this.rewardForMiner = 100;
		this.blocks = new ArrayList<>();
		this.store = new Store();
		this.load();
	}
	
	public static EzBlockchain getInstance() {
		return INSTANCE;
	}
	
	public void addBlock(EzBlock block) {
		synchronized (blocks) {
			blocks.add(block);
			store.append(block);
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
		store.forEach(block -> {
			blocks.add(block);
		});
		if(blocks.isEmpty()) {
			EzBlock genesisBlock = createGenesisBlock();
			addBlock(genesisBlock);
		}
	}
	
	private EzBlock createGenesisBlock() {
		EzBlock genesisBlock = new EzGenesisBlock();
		genesisBlock.mine();
		return genesisBlock;
	}
	
	public static class Store {

		public void append(EzBlock block) {
			EzFileStore.getInstance().append("data/blockchain.txt", block);
		}
		
		public void forEach(Consumer<EzBlock> consumer) {
			EzFileStore.getInstance().forEach("data/blockchain.txt", EzBlock.class, consumer);
		}
		
	}
	
}
