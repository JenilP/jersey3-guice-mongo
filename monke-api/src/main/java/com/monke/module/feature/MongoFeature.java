package com.monke.module.feature;

import com.monke.utils.DatabaseInitUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MongoFeature implements Feature {
    @Inject
    DatabaseInitUtil databaseInitUtil;

    @Override
    public boolean configure(FeatureContext featureContext) {
        return databaseInitUtil.createDbs();
    }
}
