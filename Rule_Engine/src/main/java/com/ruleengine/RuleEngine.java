package com.ruleengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The RuleEngine registers rules and evaluates them against a given Context.
 */
public class RuleEngine {
    private final List<Rule> rules = new ArrayList<>();

    public void registerRule(Rule rule) {
        rules.add(rule);
    }

    /**
     * Evaluates all registered rules against the given context and fires actions
     * for those whose conditions are met. Rules are executed in order of priority.
     * @param context The context containing facts.
     */
    public void fire(Context context) {
        // Sort rules by priority (highest priority first)
        Collections.sort(rules);

        for (Rule rule : rules) {
            if (rule.evaluate(context)) {
                rule.execute(context);
            }
        }
    }
}
