package nttdata.demo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PatternRequiredConstraintValidator.class)
@Target({METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(PatternRequired.List.class)
public @interface PatternRequired {
    /** */
    int index() default 0;
    /** */
    String name() default "";
    /** */
    String regexp();
    /** */
    String regexpTextMessage() default "";
    /** */
    String message() default "Invalid entry for %s";
    /** */
    Class<?>[] groups() default {};
    /** */
    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PatternRequired[] value();
    }
}
