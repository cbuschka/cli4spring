package com.github.cbuschka.cli4spring.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
public @interface CLIOption
{
	char[] shortName();

	String[] longName();

	String description() default "";

	boolean required() default true;

	String defaultValue() default "";
}
