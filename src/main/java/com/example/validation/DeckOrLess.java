package com.example.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {DeckOrLessValidator.class})
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Repeatable(RepeatValueHolderAnnotation.class)
public @interface DeckOrLess {
	
	String message() default "com.example.validation.DeckOrLess.message";
	Class<?>[] groups() default {};
	Class< ? extends Payload>[] payload() default {};

	String property();
	String comparingProperty();
	
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List{
		DeckOrLess[] values();
	}
}
