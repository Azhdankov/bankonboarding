package ru.alfabank.practice.azhdankov.bankonboarding.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ProductDtoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductDtoConstraint {

    String message() default "Ошибка валидации входных параметров";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
