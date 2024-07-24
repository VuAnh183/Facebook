package nttdata.demo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmptyRequiredConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmptyRequired {
    /** */
    int index() default 0;
    /** */
    String type() default "";
    /** */
    String name() default "";
    /** */
    String message() default "Please enter %s";
    /** */
    Class<?>[] groups() default {};
    /** */
    Class<? extends Payload>[] payload() default {};
}
