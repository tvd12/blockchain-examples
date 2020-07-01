package com.tvd12.blockchain.example3;

public class BlockChainService {

	private final BlockCreationService blockCreationService;
	
	private BlockChainService(BlockCreationService blockCreationService) {
		this.blockCreationService = blockCreationService;
	}
	
	public BlockChain loadBlockChain() {
		BlockChain blockChain = new BlockChain();
		Block firstBlock = blockCreationService.createFirstBlock();
		blockChain.addBlock(firstBlock);
		return blockChain;
	}
	
}
