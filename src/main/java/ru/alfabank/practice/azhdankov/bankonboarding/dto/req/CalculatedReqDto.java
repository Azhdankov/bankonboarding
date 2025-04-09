package ru.alfabank.practice.azhdankov.bankonboarding.dto.req;

import lombok.Getter;
import lombok.Setter;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.ProductDto;

@Getter
@Setter
public class CalculatedReqDto {
    private ProductDto productDto;
}
