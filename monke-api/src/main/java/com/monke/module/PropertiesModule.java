package com.monke.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
@NoArgsConstructor
public class PropertiesModule extends AbstractModule {
    public static final String PROPERTIES_FILE = System.getenv("PROPERTIES_FILE");

    @SneakyThrows
    @Override public void configure() {
        Names.bindProperties(binder(), loadProperties());
    }

    private Properties loadProperties() throws IOException {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE));
            return properties;
        } catch (IOException e) {
            log.info("Could not load properties file");
            throw e;
        }
    }
}