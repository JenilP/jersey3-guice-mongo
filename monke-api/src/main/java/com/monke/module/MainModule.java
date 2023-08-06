package com.monke.module;

import com.google.inject.AbstractModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainModule extends AbstractModule {

    @SneakyThrows
    @Override
    protected void configure() {
        install(new PropertiesModule());
        install(new GuiceModule());
        install(new MongoModule());
    }
}
