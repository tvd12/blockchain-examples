package com.tvd12.my.blockchain;

import java.util.ArrayList;
import java.util.List;

import com.tvd12.ezyfox.io.EzyPrints;
import com.tvd12.myblockchain.security.EzSHA256HashBuilder;

import lombok.Getter;

@Getter
public class EzBlock {

	private final long index;
	private final boolean genesis;
	private final String previousHash;
	private final List<EzTransaction> transactions;
	private final long timestamp;
	private int nonce;
	private String hash;
	
	public EzBlock(
			long index, 
			String previousHash,
			List<EzTransaction> transactions,
			long timestamp) {
		this(index, false, previousHash, transactions, timestamp);
	}
	
	public EzBlock(
			long index, 
			boolean genesis,
			String previousHash,
			List<EzTransaction> transactions,
			long timestamp) {
		this.nonce = 0;
		this.index = index;
		this.genesis = genesis;
		this.timestamp = timestamp;
		this.previousHash = previousHash;
		this.transactions = new ArrayList<>(transactions);
		this.hash = hash();
	}
	
	public String hash() {
		return new EzSHA256HashBuilder()
				.append(index)
				.append(timestamp)
				.append(previousHash)
				.append(nonce)
				.append(transactions)
				.build();
	}
	
	public void mine(int degree) {
		String x0 = EzyPrints.insertBegin("", "0", degree);
		while(!x0.equals(hash.substring(0, degree))) {
			nonce ++;
			hash = hash();
		}
		System.out.println("mining done!!!, nonce = " + nonce + ", hash = " + hash);
	}
	
	public void verify() {
		for(EzTransaction transaction : transactions)
			transaction.verify();
	}
	
	@Override
	public boolean equals(Object obj) {
		return hash.equals(((EzBlock)obj).hash);
	}
	
	@Override
	public int hashCode() {
		return hash.hashCode();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
