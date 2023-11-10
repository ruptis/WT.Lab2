package by.bsuir.wtlab2.application.di;

import by.bsuir.wtlab2.exception.DiException;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Instantiator {
    private final Map<Integer, Object[]> cache = new HashMap<>();

    public <T> T construct(Constructor<T> constructor, InstanceBuffer instanceBuffer) throws DiException {
        var preparedParams = prepareParams(instanceBuffer);
        try {
            return constructor.newInstance(preparedParams);
        } catch (Exception e) {
            throw new DiException(e.getMessage(), e);
        } finally {
            clearBuffer(preparedParams);
        }
    }

    private Object[] prepareParams(InstanceBuffer instanceBuffer) {
        var instanceCount = instanceBuffer.getInstances().size();
        Object[] buffer;
        if (cache.containsKey(instanceCount)) {
            buffer = cache.get(instanceCount);
        } else {
            buffer = new Object[instanceCount];
            cache.put(instanceCount, buffer);
        }
        for (int i = 0; i < instanceCount; i++) {
            buffer[i] = instanceBuffer.getInstances().get(i);
        }
        return buffer;
    }

    private void clearBuffer(Object[] preparedParams) {
        Arrays.fill(preparedParams, null);
    }
}
