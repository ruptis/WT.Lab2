package by.bsuir.wtlab2.application.di;

import by.bsuir.wtlab2.exception.DiException;
import lombok.Getter;

public class DiContainer implements Destroyable {
    @Getter
    private final Resolver resolver;
    private final ContractBinder contractBinder;
    private final InstanceCache instanceCache;

    public DiContainer() {
        contractBinder = new ContractBinder();
        instanceCache = new InstanceCache();
        Instantiator instantiator = new Instantiator();
        resolver = new Resolver(contractBinder, instanceCache, instantiator);
        bindInstance(resolver);
        bindInstance(this);
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

    @Override
    public void destroy() {
        instanceCache.destroy();
    }
}
