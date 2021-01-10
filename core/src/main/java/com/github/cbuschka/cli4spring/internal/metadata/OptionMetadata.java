package com.github.cbuschka.cli4spring.internal.metadata;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class OptionMetadata
{
	private Property property;
	private List<String> shortNames;
	private List<String> longNames;
	private String description;
	private String defaultValue;

	public OptionMetadata(Property property, char[] shortNames, String[] longNames, String description, String defaultValue)
	{
		this.property = property;
		this.shortNames = IntStream.range(0, shortNames.length)
				.mapToObj((i) -> String.valueOf(shortNames[i]))
				.collect(Collectors.toList());
		this.longNames = Arrays.asList(longNames);
		this.description = description;
		this.defaultValue = defaultValue;
	}
}
