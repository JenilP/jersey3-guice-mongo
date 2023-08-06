package com.monke.conversion;

public interface Converter<F,T> {
    T convert(F from);
}
