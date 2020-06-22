package com.ynz.jpa.converter;

/**
 * converting between Dto and Entity.
 * @param <D> Dto
 * @param <E> Entity
 */
public interface converter<D, E> {
    D toDto(E e);
    E toDomain(D d);
}
