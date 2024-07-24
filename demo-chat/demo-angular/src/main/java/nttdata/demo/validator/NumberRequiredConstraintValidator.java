package nttdata.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumberRequiredConstraintValidator implements ConstraintValidator<NumberRequired, Object> {
    private NumberRequired validation;

    @Override
    public void initialize(NumberRequired validation) {
        this.validation = validation;
    }

    private String getMessage() {
        return String.format(validation.message(), validation.name());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext cxt) {
        boolean valid = true;
        if(value == null){
            valid = false;
        }
        else if (value instanceof Integer number) {
            if(number <= 0)
                valid = false;
        } else if (value instanceof Double number) {
            if(number <= 0)
                valid = false;
        }

        if (!valid) {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate(getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
