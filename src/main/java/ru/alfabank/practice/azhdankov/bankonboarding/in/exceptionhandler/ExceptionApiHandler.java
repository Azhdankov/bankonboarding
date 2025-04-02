package ru.alfabank.practice.azhdankov.bankonboarding.in.exceptionhandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.alfabank.practice.azhdankov.bankonboarding.exception.CountOfProductIsExceededException;
import ru.alfabank.practice.azhdankov.bankonboarding.exception.ProductNotFoundException;
import ru.alfabank.practice.azhdankov.bankonboarding.in.dto.resp.ErrorResponseDto;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> productNotFoundException(
            ProductNotFoundException exception) {
        return new ResponseEntity<>(
                new ErrorResponseDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CountOfProductIsExceededException.class)
    public ResponseEntity<ErrorResponseDto> countOfProductIsExceeded(
            CountOfProductIsExceededException exception) {
        return new ResponseEntity<>(
                new ErrorResponseDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> badRequest(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(
                new ErrorResponseDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> badRequest(ConstraintViolationException exception) {
        return new ResponseEntity<>(
                new ErrorResponseDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
