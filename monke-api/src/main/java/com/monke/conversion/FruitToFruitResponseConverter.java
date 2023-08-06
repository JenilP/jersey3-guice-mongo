package com.monke.conversion;

import com.monke.dao.model.Fruit;
import com.monke.resource.response.FruitResponse;

public class FruitToFruitResponseConverter implements Converter<Fruit, FruitResponse> {
    @Override
    public FruitResponse convert(Fruit from) {
        return FruitResponse.builder()
                            .name(from.getName())
                            .type(from.getType())
                            .quantity(from.getQuantity())
                            .build();
    }
}
