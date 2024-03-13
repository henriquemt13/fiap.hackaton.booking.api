package com.hackaton.booking.management.api.validator;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DocumentValidator implements ConstraintValidator<Document, String> {

    public static final Pattern DOCUMENT_PATTERN = Pattern.compile("^\\d*$");


    @Override
    public void initialize(Document constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String document, ConstraintValidatorContext constraintValidatorContext) {

        try {

            final var matcher = DOCUMENT_PATTERN.matcher(document);
            if (!matcher.matches()) {
                return false;
            }

            if (document.length() == 11) {
                new CPFValidator().assertValid(document);
                return true;
            }

            if (document.length() == 14) {
                new CNPJValidator().assertValid(document);
                return true;
            }
        } catch (Exception e) {
            log.warn("Invalid document[{}] | {}", document, e.getMessage());
        }

        return false;

    }
}