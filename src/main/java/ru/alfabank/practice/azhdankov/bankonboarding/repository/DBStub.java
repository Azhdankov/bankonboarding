package ru.alfabank.practice.azhdankov.bankonboarding.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;
import ru.alfabank.practice.azhdankov.bankonboarding.repository.entity.FutureProductEntity;
import ru.alfabank.practice.azhdankov.bankonboarding.repository.mapper.PersistanceMapper;

@Repository
public class DBStub {

    @Autowired private PersistanceMapper mapper;
    private final List<FutureProductEntity> featureProductList = new ArrayList<>();

    public DBStub() {
        featureProductList.add(
                new FutureProductEntity(UUID.randomUUID(), "first product", 2, 55.6));
        featureProductList.add(
                new FutureProductEntity(UUID.randomUUID(), "second product", 3, 50.6));
        featureProductList.add(
                new FutureProductEntity(UUID.randomUUID(), "third product", 5, 10.6));
    }

    public List<ProductModel> findAll() {
        return mapper.toProductModelList(featureProductList);
    }

    public List<ProductModel> findAllByIdIn(List<UUID> ids) {
        return mapper.toProductModelList(
                featureProductList); // без бд пока просто возвращаем все элементы
    }
}
