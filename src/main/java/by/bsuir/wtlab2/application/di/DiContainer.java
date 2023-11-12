package by.bsuir.wtlab2.application.di;

import by.bsuir.wtlab2.exception.DiException;

public class DiContainer {
    private final Resolver resolver;
    private final ContractBinder contractBinder;
    private final InstanceCache instanceCache;

    private static DiContainer instance;

    private DiContainer() {
        contractBinder = new ContractBinder();
        instanceCache = new InstanceCache();
        var instantiator = new Instantiator();
        resolver = new Resolver(contractBinder, instanceCache, instantiator);
        bindInstance(resolver);
        bindInstance(this);
    }

    public static DiContainer initialize() {
        if (instance == null) {
            instance = new DiContainer();
        }
        return instance;
    }

    public static Resolver getResolver() {
        return initialize().resolver;
    }

    public <T> void bind(Class<T> contract, Class<? extends T> implementation) {
        contractBinder.bind(contract, implementation);
    }

    public <T> void bindInstance(Class<T> contract, T instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Instance cannot be null");
        }
        instanceCache.addInstance(contract, instance);
    }

    public void bindInstance(Object instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Instance cannot be null");
        }
        instanceCache.addInstance(instance.getClass(), instance);
    }

    public <T> T resolve(Class<T> contract) throws DiException {
        return resolver.resolve(contract);
    }

    public void bind(Class<?> implementation) {
        contractBinder.bind(implementation);
    }
}
