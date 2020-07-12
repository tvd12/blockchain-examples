package com.tvd12.my.blockchain.store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.my.blockchain.EzBlock;
import com.tvd12.my.blockchain.EzTransaction;

public final class EzFileStore extends EzyLoggable {

	private final ObjectMapper objectMapper;
	private final static EzFileStore INSTANCE = new EzFileStore();
	
	private EzFileStore() {
		this.objectMapper = newObjectMapper();
	}
	
	public static EzFileStore getInstance() {
		return INSTANCE;
	}
	
	private ObjectMapper newObjectMapper() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(EzBlock.class, new EzBlockDeserializer());
		ObjectMapper objectMapper = new ObjectMapper()
				.registerModule(module);
		return objectMapper;
		
	}
	
	public void append(String fileName, Object data) {
		try {
			Path path = Paths.get(fileName);
			if(!Files.exists(path)) {
				if(path.getParent() != null)
					Files.createDirectories(path.getParent());
				Files.createFile(path);
			}
			byte[] bytes = objectMapper.writeValueAsBytes(data);
			Files.write(path, bytes, StandardOpenOption.APPEND);
			Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
		}
		catch (Exception e) {
			logger.error("append data: {} to file: {} error", data, fileName, e); 
		}
	}
	
	public <T> void forEach(String fileName, Class<T> dataType, Consumer<T> consumer) {
		try {
			Path path = Paths.get(fileName);
			if(!Files.exists(path))
				return;
			Files.lines(path)
				.forEach(line ->  {
					try {
						if(line.isEmpty())
							return;
						T data = objectMapper.readValue(line, dataType);
						consumer.accept(data);
					}
					catch (Exception e) {
						throw new IllegalStateException("read line: " + line + " to: " + dataType + " error", e);
					}
				});
		}
		catch (Exception e) {
			throw new IllegalStateException("read file: " + fileName + " error", e);
		}
	}
	
	public static class EzBlockDeserializer extends StdDeserializer<EzBlock> {
		private static final long serialVersionUID = -6791992671755811847L;

		protected EzBlockDeserializer() {
			super(EzBlock.class);
		}

		@Override
		public EzBlock deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
			JsonNode node = p.getCodec().readTree(p);
			ArrayNode transactionArray = (ArrayNode)node.get("transactions");
			List<EzTransaction> transactions = new ArrayList<>();
			for(int i = 0 ; i < transactionArray.size() ; ++i)
				transactions.add(ctx.readValue(transactionArray.get(i).traverse(), EzTransaction.class));
			return new EzBlock(
					((IntNode)node.get("index")).longValue(),
					((BooleanNode)node.get("genesis")).booleanValue(), 
					((TextNode)node.get("previousHash")).textValue(),
					((TextNode)node.get("hash")).textValue(),
					transactions,
					((IntNode)node.get("degree")).intValue(),
					((IntNode)node.get("nonce")).intValue(),
					((LongNode)node.get("timestamp")).longValue());
			
		}
		
	}
	
}
