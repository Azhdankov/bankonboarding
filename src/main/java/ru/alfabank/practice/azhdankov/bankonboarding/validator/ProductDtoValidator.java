package ru.alfabank.practice.azhdankov.bankonboarding.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.ProductDto;

public class ProductDtoValidator
        implements ConstraintValidator<ProductDtoConstraint, List<ProductDto>> {

    @Override
    public boolean isValid(
            List<ProductDto> productDtoList,
            ConstraintValidatorContext constraintValidatorContext) {
        return productDtoList.stream().allMatch(e -> e.getCount() > 0);
    }
}
