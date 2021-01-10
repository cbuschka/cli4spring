package com.github.cbuschka.cli4spring.internal.metadata;

import com.github.cbuschka.cli4spring.annotations.CLICommand;
import com.github.cbuschka.cli4spring.annotations.CLIOption;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MetadataFactory
{
	public List<CommandMetadata> build(Object bean)
	{
		return buildForClass(bean, bean.getClass());
	}

	private List<CommandMetadata> buildForClass(Object bean, Class<?> clazz)
	{
		List<CommandMetadata> metadatas = new ArrayList<>();
		for (Method method : clazz.getMethods())
		{
			CLICommand annotation = method.getAnnotation(CLICommand.class);
			if (annotation != null)
			{
				CommandMetadata metadata = buildForMethod(bean, clazz, method, annotation);
				metadatas.add(metadata);
			}
		}

		return metadatas;
	}

	private CommandMetadata buildForMethod(Object bean, Class<?> clazz, Method method, CLICommand annotation)
	{
		List<OptionMetadata> optionMetadatas = new ArrayList<>();
		for (Field field : clazz.getDeclaredFields())
		{
			CLIOption optionAnnotation = field.getAnnotation(CLIOption.class);
			if (optionAnnotation != null)
			{
				OptionMetadata optionMetadata = new OptionMetadata(new FieldProperty(field), optionAnnotation.shortName(), optionAnnotation.longName(), optionAnnotation.description(),
						optionAnnotation.defaultValue());
				optionMetadatas.add(optionMetadata);
			}
		}

		return new CommandMetadata(bean, annotation.name(), annotation.description(), method, optionMetadatas);
	}
}
