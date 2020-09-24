package com.example.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface RepeatValueHolderAnnotation {
	public DeckOrLess[] value();
}


