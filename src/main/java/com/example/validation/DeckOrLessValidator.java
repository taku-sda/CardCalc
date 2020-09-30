package com.example.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class DeckOrLessValidator implements ConstraintValidator<DeckOrLess, Object> {

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
    Optional<Integer> propertyValueOpt = Optional.ofNullable((Integer) beanWrapper.getPropertyValue(property));
    Optional<Integer> comparingPropertyValueOpt = Optional.ofNullable((Integer) beanWrapper.getPropertyValue(comparingProperty));
    Integer propertyValue = propertyValueOpt.orElse(0);
    Integer comparingPropertyValue = comparingPropertyValueOpt.orElse(0);
    int compare = propertyValue.compareTo(comparingPropertyValue);

    if (compare <= 0) {
      return true;
    } else {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(message).addPropertyNode(property).addConstraintViolation();
      return false;
    }
  }
}
