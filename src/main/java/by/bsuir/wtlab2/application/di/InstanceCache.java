package by.bsuir.wtlab2.application.di;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InstanceCache implements Destroyable {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    public <T> void addInstance(Class<? extends T> contract, Object instance) {
        instances.put(contract, instance);
    }

    public <T> Optional<T> getInstance(Class<T> contract) {
        if (instances.containsKey(contract)) {
            return Optional.of(contract.cast(instances.get(contract)));
        }
        return Optional.empty();
    }

    @Override
    public void destroy() {
        instances.values().forEach(instance -> {
            if (instance instanceof Destroyable destroyable) destroyable.destroy();
        });
    }
}
