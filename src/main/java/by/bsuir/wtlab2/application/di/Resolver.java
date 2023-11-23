package by.bsuir.wtlab2.application.di;

import by.bsuir.wtlab2.exception.DiException;

import java.lang.reflect.Constructor;
import java.util.Optional;

public class Resolver {
    private final ContractBinder contractBinder;
    private final InstanceCache instanceCache;
    private final Instantiator instantiator;

    public Resolver(ContractBinder contractBinder, InstanceCache instanceCache, Instantiator instantiator) {
        this.contractBinder = contractBinder;
        this.instanceCache = instanceCache;
        this.instantiator = instantiator;
    }

    public <T> T resolve(Class<T> contract) throws DiException {
        Optional<Class<? extends T>> boundType = contractBinder.getImplementation(contract);
        Class<? extends T> targetType = boundType.isPresent() ? boundType.get() : contract;
        return getInstance(targetType);
    }

    private <T> T getInstance(Class<? extends T> targetType) throws DiException {
        Optional<? extends T> cachedInstance = instanceCache.getInstance(targetType);
        if (cachedInstance.isPresent()) {
            return cachedInstance.get();
        }
        Object instance = createInstance(targetType);
        instanceCache.addInstance(targetType, instance);
        return targetType.cast(instance);
    }

    private <T> Object createInstance(Class<? extends T> targetType) throws DiException {
        Constructor<?> constructor = targetType.getDeclaredConstructors()[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        InstanceBuffer instanceBuffer = new InstanceBuffer();
        for (Class<?> parameterType : parameterTypes) {
            instanceBuffer.addInstance(resolve(parameterType));
        }
        return instantiator.construct(constructor, instanceBuffer);
    }

}
