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
import ru.alfabank.practice.azhdankov.bankonboarding.repository.ProductRepository;

@Service
public class CalculationService {

    @Autowired private ProductRepository productRepository;

    public List<ProductModel> getProducts(List<ProductModel> productModelList) {

        List<String> productIds =
                productModelList.stream().map(product -> String.valueOf(product.getId())).toList();

        List<ProductModel> existingProductModelList = productRepository.findAllByUuidIn(productIds);

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
                            if (!existingProduct.isExists())
                                existingProductModelList.remove(existingProduct);
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
