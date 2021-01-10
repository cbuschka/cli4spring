package com.github.cbuschka.cli4spring.internal.metadata;

import lombok.AccessLevel;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Getter
public class CommandMetadata
{
	private Object bean;
	private List<String> name;
	private String description;
	private Method method;
	@Getter(AccessLevel.PRIVATE)
	private Map<String, OptionMetadata> optionByName = new HashMap<>();

	public CommandMetadata(Object bean, String[] name, String description, Method method, List<OptionMetadata> optionMetadatas)
	{
		this.bean = bean;
		this.name = Arrays.asList(name);
		this.description = description;
		this.method = method;

		for (OptionMetadata optionMetadata : optionMetadatas)
		{
			for (String shortName : optionMetadata.getShortNames())
			{
				this.optionByName.put(shortName, optionMetadata);
			}

			for (String longName : optionMetadata.getLongNames())
			{
				this.optionByName.put(longName, optionMetadata);
			}
		}
	}

	public OptionMetadata getOptionMetadata(String name)
	{
		OptionMetadata optionMetadata = this.optionByName.get(name);
		if( optionMetadata == null ) {
			throw new NoSuchElementException("Option "+name+" not known.");
		}
		return optionMetadata;
	}
}
