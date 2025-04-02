package ru.alfabank.practice.azhdankov.bankonboarding.out.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;
import ru.alfabank.practice.azhdankov.bankonboarding.out.entity.ProductEntity;

@Component
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersistenceMapper {

    @Mapping(target = "uuid", source = "id")
    ProductEntity toProductEntity(ProductModel productModel);

    @Mapping(target = "id", source = "uuid")
    ProductModel toProductModel(ProductEntity productEntity);

    List<ProductEntity> toProductEntityList(List<ProductModel> productModelList);

    List<ProductModel> toProductModelList(List<ProductEntity> productEntityList);
}
