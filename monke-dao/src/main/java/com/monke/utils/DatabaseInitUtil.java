package com.monke.utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

import static com.mongodb.client.model.Indexes.text;
import static com.monke.constants.CollectionConstants.FRUIT_COLLECTION_NAME;

@Singleton
public class DatabaseInitUtil {

    @Inject
    MongoDatabase db;

    public boolean createDbs(){
        db.getCollection(FRUIT_COLLECTION_NAME)
            .createIndex(text("name"), new IndexOptions().unique(true));
        return true;
    }
}
