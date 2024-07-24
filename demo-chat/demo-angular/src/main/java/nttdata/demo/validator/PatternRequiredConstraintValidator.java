package nttdata.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternRequiredConstraintValidator implements ConstraintValidator<PatternRequired, String> {
    private PatternRequired validation;

    private Pattern pattern;

    @Override
    public void initialize(PatternRequired validation) {
        this.validation = validation;
        if (validation.regexpTextMessage().isEmpty()) {
            this.pattern = Pattern.compile(validation.regexp());
        } else {
            this.pattern = Pattern.compile(validation.regexpTextMessage());
        }
    }

    private String getMessage() {
        return String.format(validation.message(), validation.name());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        Matcher m = pattern.matcher(value);
        if (m.matches()) {
            return true;
        }
        cxt.disableDefaultConstraintViolation();
        cxt.buildConstraintViolationWithTemplate(this.getMessage()).addConstraintViolation();
        return false;
    }
}
