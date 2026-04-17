package com.ruleengine;

/**
 * Functional interface for defining a rule condition.
 */
@FunctionalInterface
public interface Condition {
    /**
     * Evaluates the condition based on the given context.
     * @param context The context containing facts.
     * @return true if the condition is met, false otherwise.
     */
    boolean evaluate(Context context);
}
