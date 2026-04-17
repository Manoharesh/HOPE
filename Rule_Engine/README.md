# Java Rule Engine

A lightweight, extensible, and clean Rule Engine built from scratch in Java. This engine utilizes Java's functional interfaces to provide a straightforward and elegant way to process complex rules, decoupling business logic from application code.

## 🚀 Features

- **Lightweight & Fast:** Pure Java with zero external dependencies (except JUnit for testing).
- **Extensible Rules:** Built on modern functional interfaces (`Condition` and `Action`).
- **Priority Execution:** Rules can be sorted and executed based on priority (highest first).
- **Dynamic Context Engine:** A flexible Map-based Context acts as the working memory or dictionary during evaluations.
- **Fire-All Strategy:** Rules are evaluated sequentially and triggered independently if their conditions are met.

## ⚙️ Architecture

The system evaluates rules using the following components:
* `Context`: The dictionary/working memory that holds facts.
* `Condition`: Determines *when* a behavior should happen (returns true/false).
* `Action`: Describes *what* should happen when a condition meets.
* `Rule`: Pairs together a Condition, a priority, and a series of Actions.
* `RuleEngine`: The orchestrator that registers rules, sorts them by priority, and evaluates against a context.

## 🛠 Prerequisites

- **Java 17** or higher
- **Maven** 3.6+

## 📦 Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/rule-engine.git
   cd rule-engine
   ```

2. **Compile the project:**
   ```bash
   mvn clean compile
   ```

3. **Run the example application:**
   ```bash
   mvn exec:java -Dexec.mainClass="com.ruleengine.Main"
   ```
   *Alternatively:*
   ```bash
   java -cp target/classes com.ruleengine.Main
   ```

## 📖 Usage Example

Here's an example of how you can register and fire a High-Value Customer discount rule dynamically:

```java
import com.ruleengine.*;

public class Main {
    public static void main(String[] args) {
        RuleEngine engine = new RuleEngine();

        // 1. Create a Rule
        Rule highValueRule = new Rule("HighValueCustomer", 10, context -> {
            Integer totalPurchases = context.getFact("totalPurchases", Integer.class);
            return totalPurchases != null && totalPurchases > 1000;
        });
        
        // 2. Add an Action
        highValueRule.addAction(context -> {
            System.out.println("Applying 20% high-value discount!");
            context.addFact("discount", 0.20);
        });

        // 3. Register Rule
        engine.registerRule(highValueRule);

        // 4. Create Context and Fire Engine
        Context context = new Context();
        context.addFact("totalPurchases", 1500);

        engine.fire(context);
        
        System.out.println("Assigned discount: " + context.getFact("discount"));
    }
}
```

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the issues page or create a merge request.