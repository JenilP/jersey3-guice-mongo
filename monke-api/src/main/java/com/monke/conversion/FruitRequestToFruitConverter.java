package com.monke.conversion;

import com.monke.dao.model.Fruit;
import com.monke.resource.request.FruitRequest;

public class FruitRequestToFruitConverter implements Converter<FruitRequest, Fruit> {
    @Override
    public Fruit convert(FruitRequest from) {
        return Fruit.builder()
                    .name(from.getName())
                    .type(from.getType())
                    .quantity(from.getQuantity())
                    .build();
    }
}
