package ru.alfabank.practice.azhdankov.bankonboarding.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.ProductDto;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.resp.CalculatedRespDto;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.resp.WelcomeRespDto;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;
import ru.alfabank.practice.azhdankov.bankonboarding.model.WelcomeModel;

@Component
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseMapper {
    WelcomeRespDto toWelcomeDto(WelcomeModel welcomeModel);

    @Mapping(target = "count", ignore = true)
    List<ProductDto> toListProductInfoDtoWithoutCount(List<ProductModel> productModelList);

    List<ProductDto> toListProductDto(List<ProductModel> productModelList);

    List<ProductModel> toListProductModel(List<ProductDto> productDtoList);

    ProductModel toProductModel(ProductDto productDto);

    ProductDto toProductDto(ProductModel productModel);

    default CalculatedRespDto toCalculatedRespDto(List<ProductModel> productModelList) {
        CalculatedRespDto calculatedRespDto = new CalculatedRespDto();
        double sum =
                productModelList.stream().mapToDouble(e -> (e.getPrice() * e.getCount())).sum();
        calculatedRespDto.setSum(sum);
        calculatedRespDto.setProductDtoList(toListProductInfoDtoWithoutCount(productModelList));
        return calculatedRespDto;
    }
}
