package com.ruleengine;

/**
 * Functional interface for defining an action to be taken when a rule is triggered.
 */
@FunctionalInterface
public interface Action {
    /**
     * Executes the action based on the given context.
     * @param context The context containing facts.
     */
    void execute(Context context);
}
