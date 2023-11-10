package by.bsuir.wtlab2.application.di;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ContractBinder {
    private final Map<Class<?>, Class<?>> bindings = new HashMap<>();

    public <T> void bind(Class<T> contract, Class<? extends T> implementation) {
        bindings.put(contract, implementation);
    }

    public void bind(Class<?> implementation) {
        bindings.put(implementation, implementation);
    }

    public <T> Optional<Class<? extends T>> getImplementation(Class<T> contract) {
        if (bindings.containsKey(contract)) {
            return Optional.of(bindings.get(contract).asSubclass(contract));
        }
        return Optional.empty();
    }
}
