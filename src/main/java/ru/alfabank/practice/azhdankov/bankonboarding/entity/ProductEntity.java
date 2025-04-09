package ru.alfabank.practice.azhdankov.bankonboarding.entity;

import java.math.BigInteger;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "products")
public class ProductEntity {
    @Id private BigInteger _id;

    @Indexed(unique = true)
    private String uuid;

    private String name;
    private int count;
    private double price;
    private boolean isExists;
}
