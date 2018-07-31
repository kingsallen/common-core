package com.moseeker.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	String name() default "";

	boolean nullable() default true;

	String comment() default "";

	int length() default 255;

	int precision() default 0;

	int scale() default 0;

	boolean unique() default false;
	
	String regular() default "";

	ColumnCharsetEnum charset() default ColumnCharsetEnum.utf8;

	ColumnCollateEnum collate() default ColumnCollateEnum.utf8_general_ci;

}
