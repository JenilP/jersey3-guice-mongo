package com.monke.module;

import com.google.inject.AbstractModule;
import com.monke.service.FruitService;
import com.monke.service.impl.FruitServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bindServices();
    }

    public void bindServices() {
        bind(FruitService.class).to(FruitServiceImpl.class);
    }
}