package com.github.cbuschka.cli4spring.internal.parser;

import com.github.cbuschka.cli4spring.internal.metadata.CommandMetadata;
import com.github.cbuschka.cli4spring.internal.metadata.MetadataRegistry;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CommandLineParser
{
	private final Reader reader;
	private MetadataRegistry metadataRegistry;

	public CommandLineParser(Reader reader, MetadataRegistry metadataRegistry)
	{
		this.reader = reader;
		this.metadataRegistry = metadataRegistry;
	}

	public CommandLine parseCommandLine()
	{
		RawCommandLine rawCommandLine = new RawCommandLineParser(this.reader).parseCommandLine();

		CommandMetadata commandMetadata = resolveCommand(rawCommandLine);
		if (commandMetadata != null)
		{
			List<String> arguments = new ArrayList<>();
			List<String> rawArguments = rawCommandLine.getArguments();
			if (rawArguments.size() > commandMetadata.getName().size())
			{
				arguments.addAll(rawArguments.subList(commandMetadata.getName().size(), rawArguments.size() - 1));
			}
			arguments.addAll(rawCommandLine.getPostDoubleDashArguments());
			return new CommandLine(commandMetadata, arguments, rawCommandLine.getOptions());
		}

		List<String> arguments = new ArrayList<>();
		arguments.addAll(rawCommandLine.getArguments());
		arguments.addAll(rawCommandLine.getPostDoubleDashArguments());

		return new CommandLine(null, arguments, rawCommandLine.getOptions());
	}

	private CommandMetadata resolveCommand(RawCommandLine rawCommandLine)
	{
		List<String> arguments = rawCommandLine.getArguments();
		for (int i = arguments.size(); i > 0; --i)
		{
			List<String> name = arguments.subList(0, i);
			CommandMetadata commandMetadata = metadataRegistry.getCommand(name);
			if (commandMetadata != null)
			{
				return commandMetadata;
			}
		}

		return null;
	}
}
