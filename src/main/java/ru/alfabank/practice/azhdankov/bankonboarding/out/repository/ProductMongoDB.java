package ru.alfabank.practice.azhdankov.bankonboarding.out.repository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.alfabank.practice.azhdankov.bankonboarding.out.entity.ProductEntity;

public interface ProductMongoDB extends MongoRepository<ProductEntity, BigInteger> {
    List<ProductEntity> findAllByUuidIn(List<String> uuids);
}
