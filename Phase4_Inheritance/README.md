# Phase 4: Inheritance and OOP

## Learning Objectives

- Understand inheritance (parent/child class relationships)
- Use method overriding to customize behavior
- Apply polymorphism (one type, many forms)
- Use abstract classes and interfaces

## Prerequisites

- Phase 1-3 (Loops, Static, POJO)

## Mainframe Analogy

| OOP Concept | COBOL Equivalent | Java |
|-------------|-----------------|------|
| Class | Copybook + Program | `class PolicyRecord { }` |
| Object | Record instance in WORKING-STORAGE | `PolicyRecord p = new PolicyRecord();` |
| Encapsulation | COMP fields (hidden representation) | `private` fields + getters/setters |
| Inheritance | COPY base copybook + add extra fields | `class Child extends Parent` |
| Overriding | COPY ... REPLACING a paragraph | `@Override` method |
| Polymorphism | Same CALL interface, different programs | Parent reference, child behavior |
| Abstract Class | CALL spec without implementation | `abstract class` |
| Interface | CALL USING parameter contract | `interface Auditable` |

## Why This Matters for Modernization

In COBOL, you copy the same copybook into many programs and each program processes it differently. In Java, you define a base class once and create specialized child classes that inherit and customize.

## Key Rules

1. A class can extend ONLY ONE parent (single inheritance)
2. A class can implement MULTIPLE interfaces
3. Abstract classes CANNOT be instantiated (`new AbstractClass()` = ERROR)
4. `@Override` annotation tells compiler "I'm replacing parent's method"
5. `super` keyword calls the parent's constructor or method
6. `protected` access = visible to child classes (like shared copybook fields)

## Example Files

| File | Concepts |
|------|----------|
| [Example1_BasicInheritance.java](Example1_BasicInheritance.java) | Parent/child classes, extends keyword |
| [Example2_Overriding.java](Example2_Overriding.java) | Method overriding with @Override |
| [Example3_Polymorphism.java](Example3_Polymorphism.java) | Parent reference holding child objects |
| [Example4_AbstractInterface.java](Example4_AbstractInterface.java) | Abstract classes and interfaces |

## COBOL Reference

See [CobolEquivalent.md](CobolEquivalent.md) for COPY/REPLACING examples alongside Java inheritance.
