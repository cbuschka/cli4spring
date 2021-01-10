package com.github.cbuschka.cli4spring.internal.parser;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RawCommandLine
{
	@Getter
	private final List<String> arguments;
	@Getter
	private final List<String> postDoubleDashArguments;
	@Getter
	private final Map<String, Object> options;
}
