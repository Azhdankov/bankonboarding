package ru.alfabank.practice.azhdankov.bankonboarding.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.alfabank.practice.azhdankov.bankonboarding.BaseContextTest;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.ProductDto;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.resp.CalculatedRespDto;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.resp.ErrorResponseDto;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.resp.WelcomeRespDto;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;

public class BankOnboardingITTest extends BaseContextTest {

    @Test
    void whenGetWelcomeThenOk() {
        ResponseEntity<WelcomeRespDto> response =
                restTemplate.getForEntity("/shop/welcome", WelcomeRespDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).getMessage())
                .isEqualTo("Добро пожаловать в наш чудестный магазин");
    }

    @Test
    void whenGetProductThenOk() {
        ResponseEntity<List<ProductDto>> response =
                restTemplate.exchange(
                        "/shop/product",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProductDto>>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        response.getBody()
                .forEach(
                        dto -> {
                            assertThat(dto.getId()).isNotNull();
                            assertThat(dto.getPrice()).isPositive();
                            assertThat(dto.getName()).isNotNull();
                        });
    }

    @Test
    void whenCalculateSumThenOk() {

        List<ProductModel> existingProducts = productRepository.findAll();
        List<ProductDto> requestDtoList = new ArrayList<>();

        existingProducts.forEach(
                product -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setCount(
                            product.getCount() - new Random().nextInt(product.getCount() - 1));
                    requestDtoList.add(productDto);
                });

        ResponseEntity<CalculatedRespDto> response =
                restTemplate.postForEntity("/shop/calc", requestDtoList, CalculatedRespDto.class);

        CalculatedRespDto responseBody = response.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getSum()).isPositive();

        Map<UUID, ProductModel> existingProductsMap =
                existingProducts.stream()
                        .filter(ProductModel::isExists)
                        .collect(Collectors.toMap(ProductModel::getId, Function.identity()));

        List<ProductDto> responseProducts = responseBody.getProductDtoList();

        assertThat(responseProducts)
                .hasSize(requestDtoList.size() - 1)
                .allSatisfy(
                        responseProduct -> {
                            ProductModel existingProduct =
                                    existingProductsMap.get(responseProduct.getId());
                            assertThat(existingProduct).isNotNull();
                            assertThat(responseProduct.getCount())
                                    .isLessThanOrEqualTo(existingProduct.getCount());
                            assertEquals(existingProduct.getPrice(), responseProduct.getPrice());
                            assertEquals(existingProduct.getName(), responseProduct.getName());
                            assertEquals(existingProduct.getId(), responseProduct.getId());
                        });
    }

    @Test
    void whenCalculateSumThenProductNotFound() {
        List<ProductDto> requestDtoList = Instancio.createList(ProductDto.class);

        ResponseEntity<ErrorResponseDto> response =
                restTemplate.postForEntity("/shop/calc", requestDtoList, ErrorResponseDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(Objects.requireNonNull(response.getBody()).getErrorMessage())
                .startsWith("Product with id");
    }

    @Test
    void whenCalculateSumThenCountOfProductIsExceeded() {

        List<ProductModel> existingProducts = productRepository.findAll();
        List<ProductDto> requestDtoList = new ArrayList<>();

        existingProducts.stream()
                .filter(ProductModel::isExists)
                .forEach(
                        product -> {
                            ProductDto productDto = new ProductDto();
                            productDto.setId(product.getId());
                            productDto.setCount(product.getCount() + 1);
                            requestDtoList.add(productDto);
                        });

        ResponseEntity<ErrorResponseDto> response =
                restTemplate.postForEntity("/shop/calc", requestDtoList, ErrorResponseDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(Objects.requireNonNull(response.getBody()).getErrorMessage())
                .startsWith("Count of product is exceeded. Available product");
    }
}
