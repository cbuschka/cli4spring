package com.github.cbuschka.cli4spring.annotations;

public @interface CLIOption
{
	String description();

	String[] shortNames();

	String[] longNames();

	boolean required() default true;

	String defaultValue();
}
