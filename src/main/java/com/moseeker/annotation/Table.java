package com.moseeker.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

	String name() default "";
	
	String schema() default "";

	int autoIncrement() default 1;

	TableCharsetEnum charset() default TableCharsetEnum.utf8;

	TableEngineEnum engine() default TableEngineEnum.InnoDB;

	String comment() default "";

}
