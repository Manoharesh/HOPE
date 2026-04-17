package com.ruleengine;

import java.util.HashMap;
import java.util.Map;

/**
 * Context holds the facts or data that rules will interact with.
 */
public class Context {
    private final Map<String, Object> facts = new HashMap<>();

    public void addFact(String key, Object value) {
        facts.put(key, value);
    }

    public Object getFact(String key) {
        return facts.get(key);
    }

    public <T> T getFact(String key, Class<T> type) {
        Object value = facts.get(key);
        if (type.isInstance(value)) {
            return type.cast(value);
        }
        return null;
    }

    public boolean hasFact(String key) {
        return facts.containsKey(key);
    }

    public void removeFact(String key) {
        facts.remove(key);
    }
}
