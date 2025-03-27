package ru.alfabank.practice.azhdankov.bankonboarding.in.dto.resp;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alfabank.practice.azhdankov.bankonboarding.in.dto.ProductDto;

@NoArgsConstructor
@Getter
@Setter
public class CalculatedRespDto {
    private double sum;
    private List<ProductDto> productDtoList;
}
