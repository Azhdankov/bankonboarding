package ru.alfabank.practice.azhdankov.bankonboarding.repository.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FutureProductEntity {

    private UUID id;
    private String name;
    private int count;
    private double price;
}
