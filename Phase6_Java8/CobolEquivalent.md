# Phase 6: COBOL/JCL Equivalents for Java 8 Features

Side-by-side JCL SORT and Java Stream examples.

---

## 1. SORT FIELDS = sorted()

**JCL SORT (ascending by name):**
```
//SORT EXEC PGM=SORT
//SYSIN DD *
  SORT FIELDS=(1,30,CH,A)
/*
```

**Java 8 Stream:**
```java
policies.stream()
    .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
    .forEach(p -> System.out.println(p.getName()));
```

---

## 2. INCLUDE COND = filter()

**JCL SORT (include only ACTIVE):**
```
//SYSIN DD *
  SORT FIELDS=(1,10,CH,A)
  INCLUDE COND=(31,10,CH,EQ,C'ACTIVE')
/*
```

**Java 8 Stream:**
```java
List<Policy> activePolicies = policies.stream()
    .filter(p -> p.getStatus().equals("ACTIVE"))
    .collect(Collectors.toList());
```

---

## 3. OUTREC FIELDS = map()

**JCL SORT (extract/reformat fields):**
```
//SYSIN DD *
  SORT FIELDS=COPY
  OUTREC FIELDS=(1,10,31,30)
/*
```

**Java 8 Stream:**
```java
List<String> names = policies.stream()
    .map(p -> p.getName())    // Extract just the name
    .collect(Collectors.toList());
```

---

## 4. SUM FIELDS = mapToDouble().sum()

**JCL SORT (sum amounts):**
```
//SYSIN DD *
  SORT FIELDS=(1,10,CH,A)
  SUM FIELDS=(41,9,ZD)
/*
```

**Java 8 Stream:**
```java
double total = policies.stream()
    .mapToDouble(p -> p.getSumAssured())
    .sum();
```

---

## 5. Control Break / GROUP BY = Collectors.groupingBy()

**COBOL (control break processing):**
```cobol
* Sort by status first, then process each group
PERFORM UNTIL END-OF-FILE
    READ SORTED-FILE INTO WS-RECORD
    IF WS-STATUS NOT = WS-PREV-STATUS
        IF WS-PREV-STATUS NOT = SPACES
            DISPLAY 'Status: ' WS-PREV-STATUS
                    ' Count: ' WS-GROUP-COUNT
        END-IF
        MOVE 0 TO WS-GROUP-COUNT
        MOVE WS-STATUS TO WS-PREV-STATUS
    END-IF
    ADD 1 TO WS-GROUP-COUNT
END-PERFORM.
```

**Java 8 Stream (one line!):**
```java
Map<String, List<Policy>> byStatus = policies.stream()
    .collect(Collectors.groupingBy(p -> p.getStatus()));

byStatus.forEach((status, group) ->
    System.out.println("Status: " + status + " Count: " + group.size()));
```

---

## 6. Group and Sum = Collectors.groupingBy + summingDouble

**JCL SORT (sum by key):**
```
//SYSIN DD *
  SORT FIELDS=(31,10,CH,A)
  SUM FIELDS=(41,9,ZD)
/*
```

**Java 8 Stream:**
```java
Map<String, Double> sumByType = policies.stream()
    .collect(Collectors.groupingBy(
        p -> p.getType(),
        Collectors.summingDouble(p -> p.getSumAssured())
    ));
```

---

## 7. PERFORM Paragraph by Name = Lambda

**COBOL:**
```cobol
PERFORM CALCULATE-PREMIUM.    *> Call a named paragraph

CALCULATE-PREMIUM.
    COMPUTE WS-PREMIUM = WS-SUM * 0.05.
```

**Java Lambda (inline, no separate method needed):**
```java
// Instead of defining a separate method and calling it by name,
// you write the logic inline:
double premium = sumAssured * 0.05;

// Or as a lambda passed to a function:
policies.forEach(p -> System.out.println(p.getName()));
```

---

## Summary: Complete JCL SORT to Java 8 Mapping

| JCL SORT | Java 8 Stream |
|----------|--------------|
| `SORT FIELDS=(key,A)` | `.sorted(Comparator.comparing(f))` |
| `INCLUDE COND=(f,EQ,val)` | `.filter(x -> x.getF().equals(val))` |
| `OMIT COND=(f,EQ,val)` | `.filter(x -> !x.getF().equals(val))` |
| `OUTREC FIELDS=(reformat)` | `.map(x -> transform(x))` |
| `SUM FIELDS=(amount)` | `.mapToDouble(x -> x.getAmt()).sum()` |
| Record count | `.count()` |
| First record | `.findFirst()` |
| Control break | `Collectors.groupingBy(...)` |
| Remove duplicates | `.distinct()` |
