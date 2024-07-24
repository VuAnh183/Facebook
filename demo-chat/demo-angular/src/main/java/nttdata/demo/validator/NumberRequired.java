package nttdata.demo.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NumberRequiredConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberRequired {
    /** */
    int index() default 0;
    /** */
    String type() default "";
    /** */
    String name() default "";
    /** */
    String message() default "%s must be a number greater than zero";
    /** */
    Class<?>[] groups() default {};
    /** */
    Class<? extends Payload>[] payload() default {};
}
