package ru.alfabank.practice.azhdankov.bankonboarding.exception.exceptionhandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.alfabank.practice.azhdankov.bankonboarding.dto.resp.ErrorResponseDto;
import ru.alfabank.practice.azhdankov.bankonboarding.exception.CountOfProductIsExceededException;
import ru.alfabank.practice.azhdankov.bankonboarding.exception.ProductNotFoundException;

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
