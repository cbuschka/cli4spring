package com.github.cbuschka.cli4spring.internal.metadata;

import java.lang.reflect.Field;

public class FieldProperty implements Property
{
	private final Field field;

	public FieldProperty(Field field)
	{
		this.field = field;
	}

	@Override
	public void set(Object instance, Object value) throws Exception
	{
		this.field.setAccessible(true);
		this.field.set(instance, value);
	}
}
