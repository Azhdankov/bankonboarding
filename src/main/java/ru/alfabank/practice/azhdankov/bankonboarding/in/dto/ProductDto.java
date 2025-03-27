package ru.alfabank.practice.azhdankov.bankonboarding.in.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private UUID id;
    private String name;
    private double price;
    private int count;
}
