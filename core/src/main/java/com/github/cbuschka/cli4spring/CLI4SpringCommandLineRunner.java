package com.github.cbuschka.cli4spring;

import com.github.cbuschka.cli4spring.internal.metadata.CommandMetadata;
import com.github.cbuschka.cli4spring.internal.metadata.MetadataRegistry;
import com.github.cbuschka.cli4spring.internal.metadata.OptionMetadata;
import com.github.cbuschka.cli4spring.internal.parser.CommandLine;
import com.github.cbuschka.cli4spring.internal.parser.CommandLineParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.util.Map;

@Component
public class CLI4SpringCommandLineRunner implements CommandLineRunner
{
	private MetadataRegistry metadataRegistry;

	public CLI4SpringCommandLineRunner(MetadataRegistry metadataRegistry)
	{
		this.metadataRegistry = metadataRegistry;
	}

	public void run(String[] args) throws Exception
	{
		StringReader argsReader = new StringReader(String.join(" ", args));
		CommandLine commandLine = new CommandLineParser(argsReader, metadataRegistry).parseCommandLine();
		CommandMetadata commandMetadata = commandLine.getCommandMetadata();
		if (commandMetadata == null)
		{
			throw new IllegalArgumentException("No command.");
		}

		Object commandBean = commandMetadata.getBean();
		for (Map.Entry<String, Object> optionEntry : commandLine.getOptions().entrySet())
		{
			String key = optionEntry.getKey();
			Object value = optionEntry.getValue();
			OptionMetadata optionMetadata = commandMetadata.getOptionMetadata(key);
			optionMetadata.getProperty().set(commandBean, value);
		}

		Object result = commandMetadata.getMethod().invoke(commandBean);
		if (result instanceof Integer)
		{
			System.exit((Integer) result);
		}
	}

}
