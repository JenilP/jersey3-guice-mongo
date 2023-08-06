package com.monke.conversion;

import com.google.inject.Inject;
import com.monke.dao.model.Fruit;
import com.monke.resource.request.FruitRequest;
import com.monke.resource.response.FruitResponse;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConversionService {
    @Inject
    FruitToFruitResponseConverter fruitToFruitResponseConverter;
    @Inject
    FruitRequestToFruitConverter fruitRequestToFruitConverter;

    public Fruit convert(FruitRequest from, Class<Fruit> to) {
        return fruitRequestToFruitConverter.convert(from);
    }

    public FruitResponse convert(Fruit from, Class<FruitResponse> to) {
        return fruitToFruitResponseConverter.convert(from);
    }
}
