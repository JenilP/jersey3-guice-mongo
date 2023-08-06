package com.monke.dao;

import com.monke.dao.model.Fruit;
import com.monke.exception.MonkeMongoException;

import java.util.List;

public interface FruitDao {
    Fruit createFruit(Fruit fruit) throws MonkeMongoException;
    Fruit getFruit(String name) throws MonkeMongoException;
    List<Fruit> getAllFruits() throws MonkeMongoException;
    Fruit updateFruit(Fruit fruit) throws MonkeMongoException;
    void deleteFruit(String name) throws MonkeMongoException;
}
