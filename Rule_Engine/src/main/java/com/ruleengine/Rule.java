package com.ruleengine;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single rule with a priority, a condition, and a list of actions.
 */
public class Rule implements Comparable<Rule> {
    private final String name;
    private final int priority;
    private final Condition condition;
    private final List<Action> actions = new ArrayList<>();

    public Rule(String name, int priority, Condition condition) {
        this.name = name;
        this.priority = priority;
        this.condition = condition;
    }

    public Rule addAction(Action action) {
        this.actions.add(action);
        return this;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public boolean evaluate(Context context) {
        return condition.evaluate(context);
    }

    public void execute(Context context) {
        for (Action action : actions) {
            action.execute(context);
        }
    }

    @Override
    public int compareTo(Rule other) {
        // Higher priority executes first
        return Integer.compare(other.priority, this.priority);
    }
}
