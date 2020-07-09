package com.tvd12.myblockchain.security;

import com.tvd12.ezyfox.builder.EzyBuilder;
import com.tvd12.my.blockchain.EzHashable;

public abstract class EzHashBuilder implements EzyBuilder<String> {

	private StringBuilder builder = new StringBuilder();
	
	public EzHashBuilder append(Object value) {
		if(value == null)
			this.builder.append("null");
		else if(value instanceof EzHashable)
			this.builder.append(((EzHashable)value).getHash());
		else
			this.builder.append(value);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public EzHashBuilder append(Iterable value) {
		for(Object item : value)
			append(item);
		return this;
	}
	
	@Override
	public String build() {
		String answer = hash(builder.toString());
		return answer;
	}
	
	protected abstract String hash(String chain);
	
}
