package com.github.cbuschka.cli4spring;

import com.github.cbuschka.cli4spring.metadata.MetadataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLI4SpringCommandLineRunner implements CommandLineRunner
{
	private MetadataFactory metadataFactory;

	public CLI4SpringCommandLineRunner(MetadataFactory metadataFactory)
	{
		this.metadataFactory = metadataFactory;
	}

	public void run(String[] args)
	{
		System.err.println("Huhu");
	}
}
