# Phase 6: Java 8 Features

## Learning Objectives

- Write lambda expressions (inline functions)
- Use Stream API for data processing pipelines
- Group and summarize data with Collectors
- Handle null safely with Optional
- Use method references as shorthand

## Prerequisites

- Phase 1-5 (especially Collections)

## Mainframe Analogy

Java 8 Streams are like building an inline JCL SORT with INCLUDE, OUTREC, and SUM all in one statement.

| JCL SORT Operation | Java 8 Stream | Purpose |
|-------------------|---------------|---------|
| `SORT FIELDS=(key,A)` | `.sorted(Comparator.comparing(f))` | Sort records |
| `INCLUDE COND=(field,EQ,value)` | `.filter(x -> x.getField().equals(val))` | Keep matching records |
| `OMIT COND=(field,EQ,value)` | `.filter(x -> !x.getField().equals(val))` | Remove matching records |
| `OUTREC FIELDS=(1,10,15,5)` | `.map(x -> new Output(x.f1, x.f2))` | Reformat/transform |
| `SUM FIELDS=(amount)` | `.mapToDouble(x -> x.getAmt()).sum()` | Sum a field |
| Record count | `.count()` | Count records |
| First record | `.findFirst()` | Get first match |
| Control break / GROUP BY | `.collect(Collectors.groupingBy(...))` | Group by key |
| Remove duplicates | `.distinct()` | Unique values |

## Lambda Syntax

```
No parameters:    () -> System.out.println("Hello")
One parameter:    x -> x * 2
Two parameters:   (a, b) -> a + b
Multi-line:       (x) -> { int y = x * 2; return y; }
```

## Stream Pipeline Pattern

```java
collection.stream()        // Create stream (open file)
    .filter(...)           // INCLUDE/OMIT conditions
    .map(...)              // OUTREC transformation
    .sorted(...)           // SORT FIELDS
    .collect(...)          // Write output / collect results
```

## Example Files

| File | Concepts |
|------|----------|
| [Example1_Lambda.java](Example1_Lambda.java) | Lambda expressions, method references |
| [Example2_StreamBasics.java](Example2_StreamBasics.java) | filter, map, sum, count, sorted |
| [Example3_Grouping.java](Example3_Grouping.java) | groupingBy, summingDouble |
| [Example4_Optional.java](Example4_Optional.java) | Safe null handling with Optional |
| [Example5_RealWorldPipeline.java](Example5_RealWorldPipeline.java) | Full payment processing pipeline |

## COBOL Reference

See [CobolEquivalent.md](CobolEquivalent.md) for JCL SORT examples alongside Java Streams.
