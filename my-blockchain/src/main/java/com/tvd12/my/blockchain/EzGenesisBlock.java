package com.tvd12.my.blockchain;

import java.util.Collections;

public class EzGenesisBlock extends EzBlock {

	public EzGenesisBlock() {
		super(0, true, "", Collections.emptyList(), 0, System.currentTimeMillis());
	}
	
}
