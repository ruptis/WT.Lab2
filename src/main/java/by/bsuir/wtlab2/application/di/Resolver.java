package by.bsuir.wtlab2.application.di;

import by.bsuir.wtlab2.exception.DiException;

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
        var boundType = contractBinder.getImplementation(contract);
        var targetType = boundType.isPresent() ? boundType.get() : contract;
        return getInstance(targetType);
    }

    private <T> T getInstance(Class<? extends T> targetType) throws DiException {
        var cachedInstance = instanceCache.getInstance(targetType);
        if (cachedInstance.isPresent()) {
            return cachedInstance.get();
        }
        var instance = createInstance(targetType);
        instanceCache.addInstance(targetType, instance);
        return targetType.cast(instance);
    }

    private <T> Object createInstance(Class<? extends T> targetType) throws DiException {
        var constructor = targetType.getDeclaredConstructors()[0];
        var parameterTypes = constructor.getParameterTypes();
        var instanceBuffer = new InstanceBuffer();
        for (var parameterType : parameterTypes) {
            instanceBuffer.addInstance(resolve(parameterType));
        }
        return instantiator.construct(constructor, instanceBuffer);
    }

}
