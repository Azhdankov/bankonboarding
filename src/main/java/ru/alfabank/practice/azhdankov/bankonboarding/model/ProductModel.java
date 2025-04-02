package ru.alfabank.practice.azhdankov.bankonboarding.model;

import java.util.UUID;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {
    private UUID id;
    private String name;
    private double price;
    private int count;
    private boolean isExists;
}
