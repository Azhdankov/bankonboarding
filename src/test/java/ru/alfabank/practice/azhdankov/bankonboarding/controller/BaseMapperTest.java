package ru.alfabank.practice.azhdankov.bankonboarding.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import ru.alfabank.practice.azhdankov.bankonboarding.BaseContextTest;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.resp.CalculatedRespDto;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;
import ru.alfabank.practice.azhdankov.bankonboarding.model.WelcomeModel;

public class BaseMapperTest extends BaseContextTest {

    @Test
    void toWelcomeDto() {
        WelcomeModel welcomeModel = new WelcomeModel();
        welcomeModel.setMessage("Some message");
        assertThat(welcomeModel.getMessage())
                .isEqualTo(mapper.toWelcomeDto(welcomeModel).getMessage());
    }

    @Test
    void toProductDto() {
        List<ProductModel> productModelList = Instancio.createList(ProductModel.class);
        assertThat(mapper.toListProductDto(productModelList))
                .hasSameSizeAs(productModelList)
                .allSatisfy(
                        product -> {
                            assertThat(product.getId()).isNotNull();
                            assertThat(product.getName()).isNotNull();
                            assertThat(product.getCount()).isGreaterThanOrEqualTo(0);
                            assertThat(product.getPrice()).isGreaterThanOrEqualTo(0);
                        });
    }

    @Test
    void toCalculateRespDto() {
        List<ProductModel> productModelList = Instancio.createList(ProductModel.class);
        CalculatedRespDto calculatedRespDto = mapper.toCalculatedRespDto(productModelList);

        assertThat(calculatedRespDto)
                .isNotNull()
                .extracting(CalculatedRespDto::getSum)
                .isEqualTo(
                        productModelList.stream()
                                .mapToDouble(e -> (e.getPrice() * e.getCount()))
                                .sum());

        assertThat(calculatedRespDto.getProductDtoList())
                .hasSameSizeAs(productModelList)
                .allSatisfy(
                        dto -> {
                            ProductModel product =
                                    productModelList.stream()
                                            .filter(model -> model.getId().equals(dto.getId()))
                                            .findFirst()
                                            .orElseThrow();

                            assertThat(dto.getPrice()).isEqualTo(product.getPrice());
                            assertThat(dto.getCount()).isEqualTo(product.getCount());
                            assertThat(dto.getName()).isEqualTo(product.getName());
                        });
    }
}
