package ru.alfabank.practice.azhdankov.bankonboarding.exception;

public class CountOfProductIsExceededException extends RuntimeException {
    public CountOfProductIsExceededException(String id, int count) {
        super("Count of product is exceeded. Available product(" + id + ") count is: " + count);
    }
}
