package com.jackbourner.reactthymeleaf.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {


    @Override
    public boolean isValid(final String password, ConstraintValidatorContext context) {
        try {
            System.out.println("Start");
            PasswordValidator validator = new PasswordValidator(Arrays.asList(
                    // at least 8 characters
                    new LengthRule(8, 30),

                    // at least one upper-case character
                    new CharacterRule(EnglishCharacterData.UpperCase, 1),

                    // at least one lower-case character
                    new CharacterRule(EnglishCharacterData.LowerCase, 1),

                    // at least one digit character
                    new CharacterRule(EnglishCharacterData.Digit, 1),

                    // at least one symbol (special character)
                    new CharacterRule(EnglishCharacterData.Special, 1),

                    // no whitespace
                    new WhitespaceRule()

            ));
            System.out.println("Created list. Start validation");

            RuleResult result = validator.validate(new PasswordData(password));
            System.out.println("Validated");
            if (result.isValid()) {
                return true;
            }
            List<String> messages = validator.getMessages(result);
            System.out.println(messages);
            String messageTemplate = String.join(",", messages);
            context.buildConstraintViolationWithTemplate(messageTemplate)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }catch (Exception e){
            System.out.println("Error: " + e);
            return false;
        }
    }
}