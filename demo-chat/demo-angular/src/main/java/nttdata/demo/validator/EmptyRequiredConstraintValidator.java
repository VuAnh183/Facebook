package nttdata.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmptyRequiredConstraintValidator implements ConstraintValidator<EmptyRequired, Object> {
    private EmptyRequired validation;

    @Override
    public void initialize(EmptyRequired validation) {
        this.validation = validation;
    }

    private String getMessage() {
        return String.format(validation.message(), validation.name());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext cxt) {
        boolean valid = true;
        if (value == null) {
            valid = false;
        } else if (value instanceof String s) {
            if (s.trim().isEmpty()) {
                valid = false;
            }
        } else if (value instanceof List list) {
            if (list.isEmpty()) {
                valid = false;
            }
        } else if (value instanceof String[] array) {
            if (array.length == 0)
                valid = false;
        }

        if (!valid) {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate(this.getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
