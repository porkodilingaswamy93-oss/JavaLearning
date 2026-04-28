# Interview Questions: Mainframe Modernization

These questions are your differentiator — most Java developers cannot answer them.

---

## Q1: How do you convert a COBOL copybook to a Java class?

**Answer:**

```cobol
01 WS-POLICY-RECORD.
   05 WS-POLICY-ID     PIC X(10).    -> String policyId
   05 WS-AMOUNT        PIC 9(7)V99.  -> BigDecimal amount  (use BigDecimal for money!)
   05 WS-STATUS        PIC X(10).    -> String status
   05 WS-DATE          PIC 9(08).    -> LocalDate date
   05 WS-COUNT         PIC 9(05).    -> int count
   05 WS-FLAG          PIC X(01).    -> boolean flag  (Y/N -> true/false)
```

Steps:
1. Map each PIC clause to a Java type (see [DATATYPE_MAPPING.md](../DATATYPE_MAPPING.md))
2. Create a POJO with private fields, getters, setters
3. Add `@Entity` and `@Column` for database mapping
4. Use `BigDecimal` for money (never `double`!)
5. Handle 88-level conditions as `boolean` or `enum`

---

## Q2: How do you handle COBOL REDEFINES in Java?

**Answer:**

COBOL REDEFINES = same memory area, different interpretation. Java doesn't have this directly.

**Options:**

1. **Separate fields** for each interpretation:
```java
String rawField;       // Original field
int year;              // Parsed from rawField
int month;
int day;
```

2. **Parsing method** based on a type indicator:
```java
if (recordType.equals("LIFE")) {
    // Parse as life policy fields
} else if (recordType.equals("AUTO")) {
    // Parse as auto policy fields
}
```

3. **Inheritance** (preferred for type-variant records):
```java
abstract class Transaction { /* common fields */ }
class PremiumTransaction extends Transaction { /* premium-specific */ }
class LoanTransaction extends Transaction { /* loan-specific */ }
```

---

## Q3: How do you approach mainframe-to-Java migration?

**Answer (from Farmers Life experience):**

1. **ANALYZE** — Document existing COBOL programs: business rules, data flows, processing logic. Understand what each program does before converting.

2. **MAP** — Convert copybooks to Java entities (POJOs). Create the data type mapping document.

3. **CONVERT** — Translate CICS online screens to REST APIs. Each CICS transaction becomes a REST endpoint.

4. **TRANSLATE** — Rewrite batch COBOL logic as Spring Boot services. Each PERFORM paragraph becomes a Java method.

5. **REPLACE** — Swap VSAM/DB2 with PostgreSQL. Use Spring Data JPA for database access.

6. **TEST** — Functional parity testing: same input must produce same output.

7. **PARALLEL TEST** — Run both systems processing the same data. Compare results.

8. **CUTOVER** — Migrate incrementally. Start with non-critical components. Keep mainframe as fallback.

---

## Q4: What challenges did you face in modernization?

**Answer tips (based on experience):**

| Challenge | Solution |
|-----------|----------|
| COMP-3 packed decimal precision differences with Java `double` | Use `BigDecimal` for all financial calculations |
| COBOL date formats (PIC 9(8) YYYYMMDD) | Java `LocalDate` with `DateTimeFormatter` |
| REDEFINES structures with no direct Java equivalent | Use inheritance or parsing methods |
| VSAM file-level locking vs database row-level locking | Design for optimistic locking in JPA |
| Batch job sequencing (Control-M dependencies) | Spring Batch job orchestration |
| Business rules embedded deep in nested PERFORMs | Extract and document before converting |
| Mainframe team and Java team speaking different languages | Create mapping documents, lead bridge sessions |
| Testing data differences (EBCDIC vs ASCII) | Data conversion utilities, character encoding handling |

---

## Q5: How did you bridge mainframe and Java teams?

**Answer structure:**
- Created COBOL-to-Java mapping documents (like [DATATYPE_MAPPING.md](../DATATYPE_MAPPING.md))
- Led knowledge transfer sessions explaining COBOL concepts to Java developers
- Reviewed Java code to ensure business logic parity with COBOL
- Defined testing strategy comparing mainframe output with Java output
- Acted as translator between teams using analogies (CICS = REST, copybook = POJO, etc.)
