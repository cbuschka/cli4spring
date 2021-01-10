package com.github.cbuschka.cli4spring.example;

import com.github.cbuschka.cli4spring.annotations.CLICommand;
import com.github.cbuschka.cli4spring.annotations.CLIOption;
import org.springframework.stereotype.Component;

@Component
public class HelloSubCommand
{
	@CLIOption(shortName = 'm', longName = "message")
	private String message;

	@CLICommand(name = {"hello", "say"})
	public void say()
	{
		System.err.println(String.format("Hello %s", message));
	}
}
