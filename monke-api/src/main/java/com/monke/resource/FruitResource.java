package com.monke.resource;

import com.monke.conversion.ConversionService;
import com.monke.dao.model.Fruit;
import com.monke.exception.MonkeMongoException;
import com.monke.resource.request.FruitRequest;
import com.monke.resource.response.FruitResponse;
import com.monke.service.FruitService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/fruit")
@Slf4j
public class FruitResource {

    @Inject
    FruitService fruitService;

    @Inject
    ConversionService conversionService;

    @POST
    @Produces(APPLICATION_JSON)
    public FruitResponse createFruit(@NonNull FruitRequest fruitRequest) throws MonkeMongoException {
        try {
            Fruit fruit = conversionService.convert(fruitRequest, Fruit.class);
            return conversionService.convert(fruitService.createFruit(fruit),
                                             FruitResponse.class);
        } catch (Exception e) {
            log.error("Exception in FruitResource:", e);
            throw e;
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public FruitResponse getFruitByName(@QueryParam("name") String name) throws MonkeMongoException {
        try {
            return conversionService.convert(fruitService.getFruitByName(name),
                                             FruitResponse.class);
        } catch (Exception e) {
            log.error("Exception in FruitResource:", e);
            throw e;
        }
    }

    @GET
    @Path("/all")
    @Produces(APPLICATION_JSON)
    public List<FruitResponse> getAllFruits() throws MonkeMongoException {
        try {
            return fruitService.getAllFruits()
                           .stream()
                           .map(fruit -> conversionService.convert(fruit, FruitResponse.class))
                           .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Exception in FruitResource:", e);
            throw e;
        }
    }

    @POST
    @Path("/update")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public FruitResponse updateFruit(@NonNull FruitRequest fruitRequest) throws MonkeMongoException {
        try {
            Fruit fruit = conversionService.convert(fruitRequest, Fruit.class);
            return conversionService.convert(fruitService.updateFruit(fruit),
                                             FruitResponse.class);
        } catch (Exception e) {
            log.error("Exception in FruitResource:", e);
            throw e;
        }
    }

    @DELETE
    @Produces(APPLICATION_JSON)
    public void deleteFruitByName(@QueryParam("name") String name) throws MonkeMongoException {
        try {
            fruitService.deleteFruitByName(name);
        } catch (Exception e) {
            log.error("Exception in FruitResource:", e);
            throw e;
        }
    }
}
