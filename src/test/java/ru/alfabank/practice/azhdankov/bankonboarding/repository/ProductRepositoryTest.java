package ru.alfabank.practice.azhdankov.bankonboarding.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.alfabank.practice.azhdankov.bankonboarding.BaseContextTest;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;

public class ProductRepositoryTest extends BaseContextTest {

    @Test
    public void shouldHaveDataInDatabase() {
        assertThat(productRepository.findAll()).isNotEmpty();
        assertThat(productRepository.findAll().size()).isEqualTo(4);
    }

    @Test
    public void shouldFindAllByUuidInReturnResult() {
        List<String> uuidList = new ArrayList<>();
        uuidList.add("550e8400-e29b-41d4-a716-446655440000");
        uuidList.add("550e8400-e29b-41d4-a716-446655440002");
        List<ProductModel> productModelList = productRepository.findAllByUuidIn(uuidList);
        assertThat(productModelList).isNotNull().allSatisfy(e -> uuidList.contains(e.getId()));
    }
}
