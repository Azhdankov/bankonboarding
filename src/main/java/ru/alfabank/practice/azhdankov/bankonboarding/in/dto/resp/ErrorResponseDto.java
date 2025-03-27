package ru.alfabank.practice.azhdankov.bankonboarding.in.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {
    private String errorMessage;
}
