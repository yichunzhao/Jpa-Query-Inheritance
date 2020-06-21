package com.ynz.jpa.converter;

public interface converter<S, T> {
    T toDto(S s);
}
