package com.inditex.pricing.domain.services;

import com.inditex.pricing.domain.exceptions.IdGenerationException;

public interface IdGenerator<T> {
    T generate();

    default T generateOrThrow() {
        T id = generate();
        if (id == null || (id instanceof Number num && num.longValue() <= 0)) {
            throw new IdGenerationException("Failed to generate valid ID");
        }
        return id;
    }
}
