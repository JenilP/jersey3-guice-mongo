package com.monke.service;

import com.monke.dao.model.Fruit;
import com.monke.exception.MonkeMongoException;

import java.util.List;

public interface FruitService {
    Fruit createFruit(Fruit fruit) throws MonkeMongoException;
    Fruit getFruitByName(String name) throws MonkeMongoException;
    List<Fruit> getAllFruits() throws MonkeMongoException;
    Fruit updateFruit(Fruit fruit) throws MonkeMongoException;
    void deleteFruitByName(String name) throws MonkeMongoException;
}
