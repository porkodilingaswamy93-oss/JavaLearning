# Phase 2: Static Keyword in Java

## Learning Objectives

- Understand the difference between static (class-level) and instance (object-level) members
- Use static variables, methods, constants, and blocks
- Map static concepts to COBOL WORKING-STORAGE and LOCAL-STORAGE

## Prerequisites

- Phase 1 (Loops)
- Basic COBOL knowledge (WORKING-STORAGE vs LOCAL-STORAGE)

## Mainframe Analogy

The key insight: **`static` means "belongs to the CLASS, not to any single object."**

| Concept | COBOL Equivalent | Java |
|---------|-----------------|------|
| Static variable | WORKING-STORAGE shared field (one copy in CICS region) | `static int count = 0;` |
| Instance variable | LOCAL-STORAGE per-task field (each CICS task gets its own) | `String name;` |
| Static method | Utility COPY paragraph (callable without instance data) | `static void doSomething()` |
| Instance method | Paragraph using record-specific data | `void process()` |
| Static final | 78-level / VALUE constant | `static final int MAX = 1000;` |
| Static block | Initialization code at start of batch job | `static { ... }` |

## Rules

1. Static methods can ONLY access static variables (not instance variables)
2. Instance methods can access BOTH static and instance variables
3. Static methods are called on the CLASS: `ClassName.method()`
4. Instance methods are called on an OBJECT: `object.method()`
5. `static final` = constant, cannot be changed after initialization

## When to Use Static

- **Utility methods** that don't need object state (e.g., `calculateTax(amount)`)
- **Constants** shared across all instances (e.g., `MAX_POLICIES`, `TAX_RATE`)
- **Counters** tracking totals across all objects (e.g., `totalPoliciesProcessed`)
- **Configuration** loaded once at startup (e.g., system environment)

## Example Files

| File | Concepts |
|------|----------|
| [Example1_StaticVsInstance.java](Example1_StaticVsInstance.java) | Static vs instance variables and methods |
| [Example2_Constants.java](Example2_Constants.java) | Static final constants |
| [Example3_UtilityClass.java](Example3_UtilityClass.java) | Static utility methods and static blocks |

## COBOL Reference

See [CobolEquivalent.md](CobolEquivalent.md) for full COBOL code samples alongside Java.
