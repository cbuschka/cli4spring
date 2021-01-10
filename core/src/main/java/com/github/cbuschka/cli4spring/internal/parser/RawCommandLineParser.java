package com.github.cbuschka.cli4spring.internal.parser;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RawCommandLineParser
{
	private final WordReader reader;

	private final Map<String, Object> options = new HashMap<>();
	private final List<String> arguments = new ArrayList<>();
	private final List<String> postDoubleDashArguments = new ArrayList<>();

	public RawCommandLineParser(Reader reader)
	{
		this.reader = new WordReader(reader);
	}

	public RawCommandLine parseCommandLine()
	{
		boolean doubleDashSeen = false;
		while (true)
		{
			String word = this.reader.read();
			if (word == null)
			{
				break;
			}
			else if (!doubleDashSeen && word.equals("--"))
			{
				doubleDashSeen = true;
			}
			else if (!doubleDashSeen && word.startsWith("--"))
			{
				splitOption(word.substring(2));
			}
			else if (!doubleDashSeen && (word.startsWith("-")))
			{
				splitOption(word.substring(1));
			}
			else if (!doubleDashSeen)
			{
				arguments.add(word);
			}
			else
			{
				postDoubleDashArguments.add(word);
			}
		}

		return new RawCommandLine(arguments, postDoubleDashArguments, options);
	}

	private void splitOption(String option)
	{
		int eqIndex = option.indexOf("=");
		if (eqIndex == -1)
		{
			this.options.put(option, null);
			return;
		}

		String key = option.substring(0, eqIndex);
		String value = option.substring(eqIndex + 1);
		this.options.put(key, value);
	}
}
