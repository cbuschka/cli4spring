package com.github.cbuschka.cli4spring.internal.metadata;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetadataRegistry
{
	private Map<List<String>, CommandMetadata> commandMetadataMap = new ConcurrentHashMap<>();

	public void addCommand(CommandMetadata commandMetadata)
	{
		this.commandMetadataMap.put(commandMetadata.getName(), commandMetadata);
	}

	public CommandMetadata getCommand(List<String> name)
	{
		return commandMetadataMap.get(name);
	}
}
