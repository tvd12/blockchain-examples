package com.tvd12.my.blockchain;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.tvd12.ezyfox.util.EzyLoggable;

public class EzMineSchedule extends EzyLoggable {

	private final ScheduledExecutorService executorService;
	private final static EzMineSchedule INSTANCE = new EzMineSchedule();
	
	private EzMineSchedule() {
		this.executorService = Executors.newSingleThreadScheduledExecutor();
	}
	
	public static EzMineSchedule getInstance() {
		return INSTANCE;
	}
	
	public void start() {
		this.executorService.scheduleAtFixedRate(this::mine, 3, 3, TimeUnit.SECONDS);
 	}
	
	private void mine() {
		try {
			doMine();
		}
		catch (Exception e) {
			System.out.println("mine error!");
			e.printStackTrace();
		}
	}
	
	private void doMine() {
		EzTransactionPool transactionPool = EzTransactionPool.getInstance();
		List<EzTransaction> transactions = transactionPool.pollAll();
		EzBlockchain blockchain = EzBlockchain.getInstance();
		int chainSize = blockchain.size();
		int maxFreeBlocks = blockchain.getMaxFreeBlocks();
		if(transactions.isEmpty() && chainSize >= maxFreeBlocks) {
			System.out.println("transaction list is empty, do nothing");
			return;
		}
		EzBlock lastBlock = blockchain.getLastBlock();
		EzBlock newBlock = new EzBlock(
				chainSize, 
				lastBlock.getHash(), 
				transactions, 
				System.currentTimeMillis()
		);
		newBlock.mine(blockchain.getDegree());
		blockchain.addBlock(newBlock);
	}
	
}
