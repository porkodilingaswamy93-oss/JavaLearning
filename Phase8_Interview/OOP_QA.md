# Interview Questions: OOP (Object-Oriented Programming)

---

## Q1: What are the 4 pillars of OOP?

**Answer:**

| Pillar | Definition | COBOL Analogy |
|--------|-----------|---------------|
| **Encapsulation** | Hide internal data, expose via methods | COMP fields that callers can't see directly |
| **Inheritance** | Child class inherits from parent | COPY base-copybook and adding fields |
| **Polymorphism** | Same method, different behavior per type | CALL 'PROGRAM' where different programs handle the same COMMAREA differently |
| **Abstraction** | Show only what's needed, hide complexity | Calling a subprogram — you pass COMMAREA, don't care about internal paragraphs |

---

## Q2: Method overloading vs overriding?

**Answer:**

| | Overloading | Overriding |
|-|-------------|-----------|
| What | Same method name, different parameters | Child redefines parent's method (same signature) |
| Where | Same class | Child class |
| Binding | Compile-time | Runtime |
| COBOL analogy | EVALUATE TRUE with different conditions | COPY ... REPLACING a paragraph |

```java
// Overloading — same name, different parameters
double calculatePremium(double sumAssured) { return sumAssured * 0.05; }
double calculatePremium(double sumAssured, int term) { return sumAssured * 0.05 * term; }

// Overriding — child redefines parent's method
class Policy {
    double calculatePremium() { return sumAssured * 0.03; }  // Default 3%
}
class LifePolicy extends Policy {
    @Override
    double calculatePremium() { return getSumAssured() * 0.05; }  // Life 5%
}
```

---

## Q3: Abstract class vs interface?

**Answer:**

| | Abstract Class | Interface |
|-|---------------|-----------|
| Methods | Can have both complete and abstract methods | Only method signatures (Java 8+ allows default methods) |
| Inheritance | A class can extend ONE abstract class | A class can implement MULTIPLE interfaces |
| Variables | Can have instance variables | Only constants (public static final) |
| Constructor | Can have constructors | No constructors |
| COBOL analogy | Base program with some paragraphs coded, some left for caller | CALL parameter specification — defines WHAT, not HOW |

**When to use which:**
- Abstract class: When child classes share common code and state
- Interface: When unrelated classes need to follow the same contract

---

## Q4: What is encapsulation and why is it important?

**Answer:**

Encapsulation means making fields `private` and providing `public` getters/setters. This controls how data is accessed and modified.

```java
class Policy {
    private double sumAssured;  // Hidden from outside

    public double getSumAssured() { return sumAssured; }

    public void setSumAssured(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Cannot be negative");
        this.sumAssured = amount;
    }
}
```

Benefits:
- **Validation:** Setter can reject invalid data (like COBOL 88-level conditions)
- **Control:** You decide what callers can read/write
- **Flexibility:** You can change internal representation without affecting callers
