package by.bsuir.wtlab2.application.di;

import java.util.ArrayList;
import java.util.List;

public class InstanceBuffer {
    private final List<Object> instances = new ArrayList<>(32);

    public void addInstance(Object instance) {
        instances.add(instance);
    }

    public List<Object> getInstances() {
        return instances;
    }

    public void clear() {
        instances.clear();
    }
}
