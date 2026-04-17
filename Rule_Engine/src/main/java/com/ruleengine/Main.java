package com.ruleengine;

public class Main {
    public static void main(String[] args) {
        RuleEngine engine = new RuleEngine();

        // Rule 1: High value customer rule
        Rule highValueRule = new Rule("HighValueCustomer", 10, context -> {
            Integer totalPurchases = context.getFact("totalPurchases", Integer.class);
            return totalPurchases != null && totalPurchases > 1000;
        });
        highValueRule.addAction(context -> {
            System.out.println("Action executed for high value customer!");
            context.addFact("discount", 0.20); // 20% discount
        });

        // Rule 2: New customer promotion
        Rule newCustomerRule = new Rule("NewCustomerPromotion", 5, context -> {
            Boolean isNew = context.getFact("isNewCustomer", Boolean.class);
            return Boolean.TRUE.equals(isNew);
        });
        newCustomerRule.addAction(context -> {
            System.out.println("Action executed for new customer!");
            context.addFact("freeShipping", true);
        });

        // Rule 3: Regular discount rule
        Rule regularDiscountRule = new Rule("RegularDiscount", 1, context -> {
            Integer totalPurchases = context.getFact("totalPurchases", Integer.class);
            return totalPurchases != null && totalPurchases <= 1000 && totalPurchases > 500;
        });
        regularDiscountRule.addAction(context -> {
            System.out.println("Action executed for regular customer.");
            context.addFact("discount", 0.10); // 10% discount
        });

        // Register rules
        engine.registerRule(regularDiscountRule);
        engine.registerRule(highValueRule);
        engine.registerRule(newCustomerRule); // Will execute before regular discount, after high value

        // Test Case 1: High Value Customer
        System.out.println("--- Test Case 1 ---");
        Context context1 = new Context();
        context1.addFact("totalPurchases", 1500);
        context1.addFact("isNewCustomer", false);
        engine.fire(context1);
        System.out.println("Assigned discount: " + context1.getFact("discount") + "\n");

        // Test Case 2: New Customer with small purchase
        System.out.println("--- Test Case 2 ---");
        Context context2 = new Context();
        context2.addFact("totalPurchases", 200);
        context2.addFact("isNewCustomer", true);
        engine.fire(context2);
        System.out.println("Assigned discount: " + context2.getFact("discount"));
        System.out.println("Free shipping: " + context2.getFact("freeShipping") + "\n");
        
        // Test Case 3: Regular Customer
        System.out.println("--- Test Case 3 ---");
        Context context3 = new Context();
        context3.addFact("totalPurchases", 700);
        context3.addFact("isNewCustomer", false);
        engine.fire(context3);
        System.out.println("Assigned discount: " + context3.getFact("discount"));
    }
}
