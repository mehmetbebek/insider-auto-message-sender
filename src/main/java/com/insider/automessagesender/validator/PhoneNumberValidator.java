package com.insider.automessagesender.validator;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {

    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    private String message;

    @Override
    public void initialize(final PhoneNumberConstraint constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final String input, final ConstraintValidatorContext context) {
        if (!StringUtils.hasText(input)) {
            return false;
        }

        try {
            Phonenumber.PhoneNumber trPhoneNumberConstraint = phoneUtil.parse(input, "TR");
            return phoneUtil.isPossibleNumber(trPhoneNumberConstraint);
        } catch (Exception e) {
            return false;
        }
    }
}
