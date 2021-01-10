package com.github.cbuschka.cli4spring.metadata;

import java.util.ArrayList;
import java.util.List;

public class MetadataFactory
{
	private List<Object> instances = new ArrayList<>();

	public MetadataFactory()
	{

	}

	public void addSubCommand(Object subCommandInstance)
	{
		this.instances.add(subCommandInstance);
	}


	public void addConfig(Object configInstance)
	{
		this.instances.add(configInstance);
	}
	/*
	public void addSubCommand(Object subCommandInstance)
	{
		CLISubCommand subCommandAnnotation = subCommandInstance.getClass().getAnnotation(CLISubCommand.class);
		String subCommandName = subCommandAnnotation.name();
		this.subCommandInstanceByName.put(subCommandName, subCommandInstance);
	}

	private static class SubCommandMetadata
	{
		private Object bean;
		private Options options;
	}

	 */

	/*
	public SubCommandMetadata getMetadataFor(Object instance)
	{
		SubCommandMetadata subCommandMetadata = this.metadataByClass.get(instance.getClass());
		if (subCommandMetadata == null)
		{
			subCommandMetadata = createFor(instance.getClass());
			this.metadataByClass.put(instance.getClass(), subCommandMetadata);
		}

		return subCommandMetadata;
	}

	private SubCommandMetadata createFor(Class<?> implementationClass)
	{
		return new SubCommandMetadata();
	}

	public static class SubCommandMetadata
	{
		private Class<?> implementationClass;

		private String name;

		private Map<String, OptionMetadata> options;
	}

	public static class OptionMetadata
	{


	}

	 */
}
