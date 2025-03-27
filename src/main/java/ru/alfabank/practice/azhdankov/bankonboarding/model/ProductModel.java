package ru.alfabank.practice.azhdankov.bankonboarding.model;

import java.util.UUID;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {
    UUID id;
    String name;
    double price;
    int count;
}
