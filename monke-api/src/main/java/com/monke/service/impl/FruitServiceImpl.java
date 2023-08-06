package com.monke.service.impl;

import com.google.inject.Inject;
import com.monke.dao.FruitDao;
import com.monke.dao.model.Fruit;
import com.monke.exception.MonkeMongoException;
import com.monke.service.FruitService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import java.util.List;

@Slf4j
public class FruitServiceImpl implements FruitService {

	@Inject
	FruitDao fruitDao;

	@Override
	public Fruit createFruit(@NonNull Fruit fruit) throws MonkeMongoException {
		log.info("creating fruit");
		Validate.notNull(fruit.getName(), "Name cannot be null");
		return fruitDao.createFruit(fruit);
	}

	@Override
	public Fruit getFruitByName(@NonNull String name) throws MonkeMongoException {
		log.info("getting fruit");
		return fruitDao.getFruit(name);
	}

	@Override
	public List<Fruit> getAllFruits() throws MonkeMongoException {
		log.info("getting all fruits");
		return fruitDao.getAllFruits();
	}

	@Override
	public Fruit updateFruit(@NonNull Fruit fruit) throws MonkeMongoException {
		log.info("updating fruit");
		Validate.notNull(fruit.getName(), "Name cannot be null");
		return fruitDao.updateFruit(fruit);
	}

	@Override
	public void deleteFruitByName(@NonNull String name) throws MonkeMongoException {
		log.info("deleting fruit");
		fruitDao.deleteFruit(name);
	}

}