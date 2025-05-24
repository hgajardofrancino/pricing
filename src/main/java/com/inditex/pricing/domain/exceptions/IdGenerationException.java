package com.inditex.pricing.domain.exceptions;

public class IdGenerationException extends RuntimeException {
    public IdGenerationException(String message) {
        super(message);
    }
}
