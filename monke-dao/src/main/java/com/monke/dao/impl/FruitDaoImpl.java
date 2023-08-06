package com.monke.dao.impl;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoServerException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.monke.dao.FruitDao;
import com.monke.dao.model.Fruit;
import com.monke.exception.MonkeMongoException;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.monke.constants.CollectionConstants.FRUIT_COLLECTION_NAME;

@Slf4j
public class FruitDaoImpl implements FruitDao {
    @Inject
    MongoDatabase db;

    private MongoCollection<Fruit> collection() {
        return db.getCollection(FRUIT_COLLECTION_NAME, Fruit.class);
    }

    @Override
    public Fruit createFruit(Fruit fruit) throws MonkeMongoException {
        try {
            collection().insertOne(fruit);
            return getFruit(fruit.getName());
        } catch (MongoWriteException e) {
            log.error("Failure to write", e);
            if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                throw new MonkeMongoException(String.format("%s already exists!", fruit.getName()));
            }
            throw new MonkeMongoException(e);
        } catch (Exception e) {
            log.error("Something bad happened", e);
            throw e;
        }
    }

    @Override
    public Fruit getFruit(String name) throws MonkeMongoException {
        try {
            return collection().find(eq("name", name)).iterator().next();
        } catch (MongoServerException e) {
            log.error("Failure getting fruit", e);
            throw new MonkeMongoException(e);
        } catch (NoSuchElementException e) {
            throw new MonkeMongoException(String.format("%s doesn't exist", name));
        } catch (Exception e) {
            log.error("Something bad happened", e);
            throw e;
        }
    }

    @Override
    public List<Fruit> getAllFruits() throws MonkeMongoException {
        try {
            MongoCursor<Fruit> tickerCursor = collection().find(Fruit.class).cursor();
            List<Fruit> fruits = new ArrayList<>();
            while (tickerCursor.hasNext()) {
                fruits.add(tickerCursor.next());
            }
            return fruits;
        } catch (MongoServerException e) {
            log.error("Failure to getting fruits", e);
            throw new MonkeMongoException(e);
        } catch (Exception e) {
            log.error("Something bad happened", e);
            throw e;
        }
    }

    @Override
    public Fruit updateFruit(Fruit fruit) throws MonkeMongoException {
        try {
            if (fruit.getQuantity() != null)
                collection().updateOne(eq("name", fruit.getName()), combine(set("quantity", fruit.getQuantity())));
            if (fruit.getType() != null)
                collection().updateOne(eq("name", fruit.getName()), combine(set("type", fruit.getType())));
            return getFruit(fruit.getName());
        } catch (MongoServerException e) {
            log.error("Failure to write", e);
            throw new MonkeMongoException(e);
        } catch (Exception e) {
            log.error("Something bad happened", e);
            throw e;
        }
    }

    @Override
    public void deleteFruit(String name) throws MonkeMongoException {
        try {
            collection().deleteOne(eq("name", name));
        } catch (MongoServerException e) {
            log.error("Failure to write", e);
            throw new MonkeMongoException(e);
        } catch (Exception e) {
            log.error("Something bad happened", e);
            throw e;
        }
    }
}