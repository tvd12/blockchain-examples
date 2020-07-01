package com.tvd12.blockchain.security;

import com.tvd12.ezyfox.builder.EzyBuilder;

public abstract class HashBuilder implements EzyBuilder<String> {

	private StringBuilder stringBuilder = new StringBuilder();
	
	public HashBuilder append(Object value) {
		this.stringBuilder.append(value);
		return this;
	}
	
	@Override
	public String build() {
		String answer = hash(stringBuilder.toString());
		return answer;
	}
	
	protected abstract String hash(String chain);
	
}
