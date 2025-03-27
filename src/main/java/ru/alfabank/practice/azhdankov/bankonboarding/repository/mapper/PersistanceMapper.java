package ru.alfabank.practice.azhdankov.bankonboarding.repository.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;
import ru.alfabank.practice.azhdankov.bankonboarding.repository.entity.FutureProductEntity;

@Component
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersistanceMapper {

    FutureProductEntity toFutureProductEntity(ProductModel productModel);

    ProductModel toProductModel(FutureProductEntity futureProductEntity);

    List<FutureProductEntity> toFutureProductEntityList(List<ProductModel> productModelList);

    List<ProductModel> toProductModelList(List<FutureProductEntity> futureProductEntityList);
}
