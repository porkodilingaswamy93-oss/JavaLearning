# Interview Questions: Java 8

---

## Q1: What is a lambda expression?

**Answer:**

A lambda is an inline, anonymous function. Instead of defining a method in a separate class, you write the logic right where you need it.

COBOL analogy: Imagine writing PERFORM logic inline instead of creating a separate paragraph.

```java
// Syntax: (parameters) -> expression
(a, b) -> a + b

// Sort with lambda
list.sort((a, b) -> a.compareTo(b));

// forEach with lambda
list.forEach(item -> System.out.println(item));
```

---

## Q2: What is the difference between map() and flatMap()?

**Answer:**

| | map() | flatMap() |
|-|-------|----------|
| Transforms | Each element one-to-one | Each element to a stream, then flattens |
| Input -> Output | `[A, B, C]` -> `[A1, B1, C1]` | `[[A,B], [C,D]]` -> `[A, B, C, D]` |
| COBOL analogy | OUTREC reformatting each record | Merging multiple files into one |

```java
// map: transform each element
List<String> names = policies.stream()
    .map(p -> p.getName())        // Policy -> String (one-to-one)
    .collect(Collectors.toList());

// flatMap: flatten nested lists
List<List<String>> nested = Arrays.asList(
    Arrays.asList("A", "B"),
    Arrays.asList("C", "D")
);
List<String> flat = nested.stream()
    .flatMap(list -> list.stream())  // Flatten
    .collect(Collectors.toList());
// Result: [A, B, C, D]
```

---

## Q3: What is Optional and why use it?

**Answer:**

Optional is a container that may or may not contain a value. It prevents `NullPointerException` (the #1 Java runtime error).

COBOL analogy: Like checking `IF WS-FIELD = SPACES` before using it.

```java
// Without Optional (risky)
Policy p = findPolicy("POL999");
String name = p.getName();  // NullPointerException if p is null!

// With Optional (safe)
Optional<Policy> p = findPolicy("POL999");
String name = p.map(pol -> pol.getName()).orElse("NOT FOUND");
```

Key methods:
- `isPresent()` — check if value exists
- `ifPresent(consumer)` — do something if value exists
- `orElse(default)` — return default if empty
- `map(function)` — transform the value if present

---

## Q4: Explain the Stream pipeline pattern.

**Answer:**

A Stream pipeline has three parts:

```java
collection.stream()        // 1. SOURCE — create the stream
    .filter(...)           // 2. INTERMEDIATE — transform/filter (lazy)
    .map(...)              //    Can chain multiple operations
    .sorted(...)
    .collect(...)          // 3. TERMINAL — produce the result (triggers execution)
```

JCL SORT mapping:
- Source = SORTIN DD
- filter = INCLUDE COND
- map = OUTREC FIELDS
- sorted = SORT FIELDS
- collect = SORTOUT DD

Streams are **lazy** — intermediate operations don't execute until a terminal operation is called.
