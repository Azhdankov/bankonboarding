package ru.alfabank.practice.azhdankov.bankonboarding.repository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.alfabank.practice.azhdankov.bankonboarding.entity.ProductEntity;

public interface ProductMongoDB extends MongoRepository<ProductEntity, BigInteger> {
    List<ProductEntity> findAllByUuidIn(List<String> uuids);
}
