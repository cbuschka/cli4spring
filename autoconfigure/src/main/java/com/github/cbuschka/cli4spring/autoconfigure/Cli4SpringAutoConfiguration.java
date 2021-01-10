package com.github.cbuschka.cli4spring.autoconfigure;

import com.github.cbuschka.cli4spring.CLI4SpringCommandLineRunner;
import com.github.cbuschka.cli4spring.CLI4SpringConfiguration;
import com.github.cbuschka.cli4spring.CLIBeanPostProcessor;
import com.github.cbuschka.cli4spring.internal.metadata.MetadataRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(Cli4SpringProperties.class)
public class Cli4SpringAutoConfiguration
{
	@Autowired
	private Cli4SpringProperties properties;

	private static MetadataRegistry metadataRegistry = new MetadataRegistry();

	@Bean
	public CLI4SpringConfiguration cli4SpringConfig()
	{
		return new CLI4SpringConfiguration();
	}

	@Bean
	public static CLIBeanPostProcessor cliBeanPostProcessor()
	{
		return new CLIBeanPostProcessor(metadataRegistry);
	}

	@Bean
	// @ConditionalOnMissingBean
	public CommandLineRunner cli4SpringCommandLineRunner()
	{
		return new CLI4SpringCommandLineRunner(metadataRegistry);
	}
}
