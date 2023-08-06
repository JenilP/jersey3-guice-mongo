package com.monke.module.feature;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.monke.module.MainModule;
import jakarta.annotation.Priority;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.InjectionManagerProvider;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

@Priority(1)
@Slf4j
public class GuiceFeature implements Feature {
    @Override
    public boolean configure(FeatureContext featureContext) {
        InjectionManager injectionManager = InjectionManagerProvider.getInjectionManager(featureContext);
        ServiceLocator serviceLocator = injectionManager.getInstance(ServiceLocator.class);
        Injector injector = Guice.createInjector(new MainModule());
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(injector);
        return true;
    }
}
