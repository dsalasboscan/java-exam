package com.davidsalas.exam.model.dto.validation;

import com.davidsalas.exam.exception.custom.InvalidEmailFormatException;
import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {

  @Override
  public void initialize(ValidEmail constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (!EmailValidator.getInstance().isValid(value)) {
      throw new InvalidEmailFormatException(value);
    }
    return true;
  }
}