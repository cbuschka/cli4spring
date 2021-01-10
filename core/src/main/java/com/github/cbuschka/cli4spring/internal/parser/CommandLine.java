package com.github.cbuschka.cli4spring.internal.parser;

import com.github.cbuschka.cli4spring.internal.metadata.CommandMetadata;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class CommandLine
{
	@Getter
	private final CommandMetadata commandMetadata;
	@Getter
	private final List<String> arguments;
	@Getter
	private final Map<String, Object> options;
}
