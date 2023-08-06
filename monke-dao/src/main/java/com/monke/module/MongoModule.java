package com.monke.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.monke.dao.FruitDao;
import com.monke.dao.impl.FruitDaoImpl;
import com.monke.dao.model.Fruit;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Optional;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoModule extends AbstractModule {
    private static final String DB_NAME = "MonkeDB";

    @Override
    protected void configure() {
        bindDaos();
    }

    public void bindDaos() {
        bind(FruitDao.class).to(FruitDaoImpl.class).in(Singleton.class);
    }

    @Provides
    public MongoClient getMongoClient(@Named("mongoURI") String mongoUri) {
        ClassModel<Fruit> tickerClassModel = ClassModel.builder(Fruit.class).enableDiscriminator(true).build();
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder()
                                                                         .register(tickerClassModel)
                                                                         .automatic(true)
                                                                         .build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        String uri = Optional.of(mongoUri).orElse(System.getenv("MONGO_URI"));
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                                                                .applyConnectionString(new ConnectionString(uri))
                                                                .codecRegistry(codecRegistry)
                                                                .build();
        return MongoClients.create(clientSettings);
    }

    @Provides
    public MongoDatabase getMongoDB(MongoClient mongoClient) {
        return mongoClient.getDatabase(DB_NAME);
    }

}
