package com.tvd12.my.blockchain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tvd12.ezyfox.io.EzyPrints;
import com.tvd12.myblockchain.security.EzSHA256HashBuilder;

import lombok.Getter;

@Getter
public class EzBlock {

	private final long index;
	private final int degree;
	private final boolean genesis;
	private final String previousHash;
	private final List<EzTransaction> transactions;
	private final long timestamp;
	private int nonce;
	private String hash;
	private final static Logger LOGGER = LoggerFactory.getLogger(EzBlock.class);
	
	public EzBlock(
			long index, 
			String previousHash,
			List<EzTransaction> transactions,
			int degree,
			long timestamp) {
		this(index, false, previousHash, transactions, degree, timestamp);
	}
	
	public EzBlock(
			long index, 
			boolean genesis,
			String previousHash,
			List<EzTransaction> transactions,
			int degree,
			long timestamp) {
		this(
				index, 
				genesis, 
				previousHash,
				"",
				transactions,
				degree, 
				0,
				timestamp
		);
		this.hash = hash();
	}
	
	public EzBlock(
			long index, 
			boolean genesis,
			String previousHash,
			String hash,
			List<EzTransaction> transactions,
			int degree,
			int nonce,
			long timestamp) {
		this.nonce = nonce;
		this.index = index;
		this.degree = degree;
		this.genesis = genesis;
		this.timestamp = timestamp;
		this.previousHash = previousHash;
		this.transactions = new ArrayList<>(transactions);
		this.hash = hash;
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
	
	public void mine() {
		String x0 = EzyPrints.insertBegin("", "0", degree);
		while(!x0.equals(hash.substring(0, degree))) {
			nonce ++;
			hash = hash();
		}
		LOGGER.info("mining done!!!, nonce = {}, hash = {}", nonce, hash);
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
