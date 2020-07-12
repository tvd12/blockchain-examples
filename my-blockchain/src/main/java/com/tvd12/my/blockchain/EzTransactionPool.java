package com.tvd12.my.blockchain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class EzTransactionPool {

	private final Queue<EzTransaction> verifiedTransactions;
	private final static EzTransactionPool INSTANCE = new EzTransactionPool();
	
	private EzTransactionPool() {
		this.verifiedTransactions = new LinkedList<>();
	}
	
	public static EzTransactionPool getInstance() {
		return INSTANCE;
	}
	
	public void add(EzTransaction transaction) {
		synchronized (verifiedTransactions) {
			verifiedTransactions.add(transaction);
		}
	}
	
	public List<EzTransaction> pollAll() {
		List<EzTransaction> list = new ArrayList<>();
		synchronized (this) {
			while(verifiedTransactions.size() > 0)
				list.add(verifiedTransactions.poll());
		}
		return list;
	}
	
}
