package com.example.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class DeckOrLessValidator implements ConstraintValidator<DeckOrLess, Object>{
	
	private String property;
	private String comparingProperty;
	private String message;
	
	public void initialize(DeckOrLess constraintAnnotation) {
		this.property = constraintAnnotation.property();
		this.comparingProperty = constraintAnnotation.comparingProperty();
		this.message = constraintAnnotation.message();
	}
	
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		Integer propertyValue = (Integer) beanWrapper.getPropertyValue(property);
		Integer comparingPropertyValue = (Integer) beanWrapper.getPropertyValue(comparingProperty);
		int compare = propertyValue.compareTo(comparingPropertyValue);

		if(compare < 0) {
			return true;
		}else {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message)
				.addPropertyNode(property).addConstraintViolation();
			return false;
		}
	}
}
