# Phase 3: POJO Classes (Plain Old Java Objects)

**THIS IS THE MOST IMPORTANT TOPIC FOR YOUR MODERNIZATION ROLE.**

## Learning Objectives

- Understand POJOs as the Java equivalent of COBOL copybooks
- Create classes with private fields, getters, setters, constructors, and toString()
- Process arrays of POJO objects like batch record processing

## Prerequisites

- Phase 1 (Loops) and Phase 2 (Static)

## Mainframe Analogy

In your Farmers Life project, this is exactly what you did: converted COBOL copybooks to Java entities. Each 01-level copybook became a Java POJO class.

| COBOL | Java | Purpose |
|-------|------|---------|
| 01-level copybook structure | POJO class | Define a record layout |
| 05-level fields | Instance variables (private) | Individual data fields |
| PIC clause (data type) | Java data type (String, int, double) | Field type |
| `MOVE value TO field` | Setter method: `setField(value)` | Write a field |
| `DISPLAY field` | Getter method: `getField()` | Read a field |
| `INITIALIZE` | Default constructor: `new Record()` | Create with defaults |
| `MOVE values TO fields` | Parameterized constructor | Create with values |

## POJO Rules

1. All fields are **private** (encapsulation — like COMP fields hidden from callers)
2. Each field has a public **getter** (to read) and **setter** (to write)
3. Class has a **default no-arg constructor**
4. Optionally: `toString()`, `equals()`, `hashCode()` methods
5. No business logic in pure POJOs (but you CAN add it for domain objects)

## Example Files

| File | Concepts |
|------|----------|
| [Example1_SimplePOJO.java](Example1_SimplePOJO.java) | Basic POJO with getters, setters, constructors |
| [Example2_PolicyRecord.java](Example2_PolicyRecord.java) | Array of POJOs, batch processing |
| [Example3_PaymentRecord.java](Example3_PaymentRecord.java) | Domain object with business method |
| [Example4_BatchProcessing.java](Example4_BatchProcessing.java) | Full batch simulation with POJOs |

## COBOL Reference

See [CobolEquivalent.md](CobolEquivalent.md) for actual copybook samples alongside Java POJOs.
Also see [../DATATYPE_MAPPING.md](../DATATYPE_MAPPING.md) for complete PIC-to-Java type reference.
