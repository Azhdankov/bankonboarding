package ru.alfabank.practice.azhdankov.bankonboarding.out;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;
import ru.alfabank.practice.azhdankov.bankonboarding.out.mapper.PersistenceMapper;
import ru.alfabank.practice.azhdankov.bankonboarding.out.repository.ProductMongoDB;

@Repository
@AllArgsConstructor
public class ProductRepository {

    private final ProductMongoDB repository;
    private final PersistenceMapper mapper;

    public List<ProductModel> findAll() {
        return mapper.toProductModelList(repository.findAll());
    }

    public List<ProductModel> findAllByUuidIn(List<String> uuids) {
        return mapper.toProductModelList(repository.findAllByUuidIn(uuids));
    }
}
