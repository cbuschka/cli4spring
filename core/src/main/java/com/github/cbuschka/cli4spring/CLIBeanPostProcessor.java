package com.github.cbuschka.cli4spring;

import com.github.cbuschka.cli4spring.annotations.CLIConfig;
import com.github.cbuschka.cli4spring.annotations.CLISubCommand;
import com.github.cbuschka.cli4spring.metadata.MetadataFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CLIBeanPostProcessor implements BeanPostProcessor
{
	private final MetadataFactory metadataFactory;

	public CLIBeanPostProcessor(MetadataFactory metadataFactory)
	{
		this.metadataFactory = metadataFactory;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
	{
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
	{
		if (isSubCommand(bean))
		{
			this.metadataFactory.addSubCommand(bean);
		}

		if (isConfig(bean))
		{
			this.metadataFactory.addConfig(bean);
		}

		return bean;
	}

	private boolean isSubCommand(Object bean)
	{
		return bean.getClass().getAnnotation(CLISubCommand.class) != null;
	}

	private boolean isConfig(Object bean)
	{
		return bean.getClass().getAnnotation(CLIConfig.class) != null;
	}
}
