package com.tvd12.blockchain.example2;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import com.tvd12.blockchain.security.SHA256HashBuilder;
import com.tvd12.ezyfox.io.EzyPrints;
import com.tvd12.ezyfox.util.EzyEquals;
import com.tvd12.ezyfox.util.EzyHashCodes;

import lombok.Getter;

@Getter
public class Block {

	private long index;
	private Date timestamp;
	private String previousHash;
	private Queue<Transaction> transactions;
	private int nonce;
	private String hash;
	
	public Block(
			long index, 
			Date timestamp, 
			String previousHash, Queue<Transaction> transactions) {
		this.index = index;
		this.timestamp = timestamp;
		this.previousHash = previousHash;
		this.transactions = new LinkedList<>(transactions);
		this.hash = hash();
	}
	
	public String hash() {
		return new SHA256HashBuilder()
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
		System.out.println("mining done!!!");
	}
	
	@Override
	public boolean equals(Object obj) {
		return new EzyEquals<Block>()
				.function(t -> t.hash)
				.isEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return new EzyHashCodes().append(hash).toHashCode();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
