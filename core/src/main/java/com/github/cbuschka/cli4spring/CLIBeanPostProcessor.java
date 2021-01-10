package com.github.cbuschka.cli4spring;

import com.github.cbuschka.cli4spring.internal.metadata.CommandMetadata;
import com.github.cbuschka.cli4spring.internal.metadata.MetadataFactory;
import com.github.cbuschka.cli4spring.internal.metadata.MetadataRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

public class CLIBeanPostProcessor implements BeanPostProcessor
{
	private static final String[] excludedBeanNamePrefixeds = {"org.springframework", "com.github.cbuschka.cli4spring"};

	private final MetadataFactory metadataFactory = new MetadataFactory();

	private final MetadataRegistry metadataRegistry;

	public CLIBeanPostProcessor(MetadataRegistry metadataRegistry)
	{
		this.metadataRegistry = metadataRegistry;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
	{
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
	{
		if (isIncluded(beanName))
		{
			List<CommandMetadata> commandMetadatas = this.metadataFactory.build(bean);
			for (CommandMetadata commandMetadata : commandMetadatas)
			{
				this.metadataRegistry.addCommand(commandMetadata);
			}

			// FIXME configs
		}

		return bean;
	}

	private boolean isIncluded(String beanName)
	{
		for (String prefix : excludedBeanNamePrefixeds)
		{
			if (beanName.startsWith(prefix))
			{
				return false;
			}
		}

		return true;
	}
}
