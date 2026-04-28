# Interview Questions: Core Java

---

## Q1: What is the difference between JDK, JRE, and JVM?

**Answer:**

| Component | What It Is | Mainframe Analogy |
|-----------|-----------|-------------------|
| JVM (Java Virtual Machine) | Runs Java bytecode | z/OS Language Environment (LE) that runs COBOL |
| JRE (Java Runtime Environment) | JVM + core libraries | LE + runtime libraries needed to execute programs |
| JDK (Java Development Kit) | JRE + compiler + tools | COBOL compiler (IGYCRCTL) + LE + ISPF editor |

- You need JDK to **develop** Java programs (compile + run)
- You need JRE to **run** Java programs (no compiler)
- JVM is the engine inside both that actually executes the code

---

## Q2: What is the difference between == and .equals()?

**Answer:**
- `==` compares **references** (memory addresses) — are these the same object?
- `.equals()` compares **values** (content) — do they contain the same data?

In COBOL, everything is value comparison (`IF WS-A = WS-B`). In Java, objects need `.equals()`.

```java
String a = new String("POL001");
String b = new String("POL001");

System.out.println(a == b);       // false (different objects in memory)
System.out.println(a.equals(b));  // true (same content)
```

**Rule: Always use `.equals()` for String comparison in Java.**

---

## Q3: What are access modifiers?

**Answer:**

| Modifier | Visibility | COBOL Analogy |
|----------|-----------|---------------|
| `public` | Accessible from anywhere | CALL-able subprogram |
| `private` | Only within the class | COMP internal fields |
| `protected` | Class + child classes | Shared copybook fields |
| (default) | Within the same package | Programs in same load library |

---

## Q4: String vs StringBuilder vs StringBuffer?

**Answer:**

| Type | Mutable? | Thread-Safe? | COBOL Analogy |
|------|---------|-------------|---------------|
| `String` | No (immutable) | Yes | 78-level constant |
| `StringBuilder` | Yes | No (fast) | WORKING-STORAGE field you STRING into |
| `StringBuffer` | Yes | Yes (slower) | Same, but safe for multi-threading |

```java
// String creates a NEW object each concatenation (wasteful in loops)
String str = "POL";
str = str + "001";  // Creates new String object

// StringBuilder modifies the SAME object (efficient)
StringBuilder sb = new StringBuilder("POL");
sb.append("001");   // Modifies in place
```

**Rule: Use `StringBuilder` when building strings in loops.**

---

## Q5: What is autoboxing and unboxing?

**Answer:**

Java has primitive types (`int`, `double`) and wrapper classes (`Integer`, `Double`). The compiler converts between them automatically.

- **Autoboxing:** `int` -> `Integer` (primitive to wrapper)
- **Unboxing:** `Integer` -> `int` (wrapper to primitive)

COBOL analogy: Like converting between `PIC 9(5)` (display numeric) and `COMP-3` (packed decimal). The compiler handles it.

```java
int primitiveNum = 100;
Integer wrapperNum = primitiveNum;  // Autoboxing
int backToPrimitive = wrapperNum;   // Unboxing
```

Wrapper classes are needed for Collections (you can't do `ArrayList<int>`, must use `ArrayList<Integer>`).
