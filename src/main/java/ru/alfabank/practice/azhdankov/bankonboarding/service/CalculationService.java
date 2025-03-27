package ru.alfabank.practice.azhdankov.bankonboarding.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfabank.practice.azhdankov.bankonboarding.exception.CountOfProductIsExceededException;
import ru.alfabank.practice.azhdankov.bankonboarding.exception.ProductNotFoundException;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;
import ru.alfabank.practice.azhdankov.bankonboarding.repository.DBStub;

@Service
public class CalculationService {

    @Autowired private DBStub dbStub;

    public List<ProductModel> getProducts(List<ProductModel> productModelList) {

        List<UUID> productIds = productModelList.stream().map(ProductModel::getId).toList();

        List<ProductModel> existingProductModelList = dbStub.findAllByIdIn(productIds);

        Map<UUID, ProductModel> existingProductsMap =
                existingProductModelList.stream()
                        .collect(Collectors.toMap(ProductModel::getId, Function.identity()));

        productModelList.stream()
                .allMatch(
                        requestedProduct -> {
                            ProductModel existingProduct =
                                    existingProductsMap.get(requestedProduct.getId());
                            if (existingProduct == null)
                                throw new ProductNotFoundException(requestedProduct.getId());
                            if (requestedProduct.getCount() > existingProduct.getCount())
                                throw new CountOfProductIsExceededException(
                                        existingProduct.getId().toString(),
                                        existingProduct.getCount());
                            else existingProduct.setCount(requestedProduct.getCount());
                            return true;
                        });

        return existingProductModelList;
    }
}
