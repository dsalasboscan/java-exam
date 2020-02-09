package com.davidsalas.exam.model.dto.validation;

import com.davidsalas.exam.exception.custom.PasswordInvalidFormatException;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

  @Override
  public void initialize(ValidPassword arg0) {
  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    PasswordValidator validator = new PasswordValidator(Arrays.asList(
        new CharacterRule(EnglishCharacterData.Digit, 2),
        new CharacterRule(EnglishCharacterData.UpperCase, 1),
        new WhitespaceRule())
    );

    RuleResult result = validator.validate(new PasswordData(password));
    if (result.isValid()) {
      return true;
    }

    throw new PasswordInvalidFormatException(result.getDetails().toString());
  }
}