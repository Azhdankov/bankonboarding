package ru.alfabank.practice.azhdankov.bankonboarding.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfabank.practice.azhdankov.bankonboarding.model.ProductModel;
import ru.alfabank.practice.azhdankov.bankonboarding.out.ProductRepository;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;

    public List<ProductModel> getProducts() {
        return productRepository.findAll();
    }
}
