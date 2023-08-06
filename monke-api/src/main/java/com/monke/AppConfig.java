package com.monke;

import com.monke.module.feature.GuiceFeature;
import com.monke.module.feature.MongoFeature;
import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class AppConfig extends ResourceConfig {
    @Inject
    public AppConfig(ServiceLocator serviceLocator) {
        packages("com.monke.resource");
        register(JacksonFeature.withExceptionMappers());
        register(new GuiceFeature());
        register(new MongoFeature());
    }
}