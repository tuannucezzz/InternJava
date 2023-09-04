package tuna.intern.internvnpt.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidUserValidator.class)
public @interface ValidUser {
    public String message() default "Invalid user!";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
