# COBOL PIC Clause to Java Data Type Mapping

Complete reference for converting COBOL data definitions to Java types.

---

## Core Mapping Table

| COBOL PIC Clause | Java Type | Example COBOL | Example Java |
|-----------------|-----------|---------------|-------------|
| `PIC X(n)` | `String` | `PIC X(30)` | `String name;` |
| `PIC 9(n)` (small) | `int` | `PIC 9(05)` | `int count;` |
| `PIC 9(n)` (large) | `long` | `PIC 9(18)` | `long bigNumber;` |
| `PIC 9(n)V9(m)` | `double` | `PIC 9(07)V99` | `double amount;` |
| `PIC 9(n)V9(m)` (money) | `BigDecimal` | `PIC 9(07)V99` | `BigDecimal premium;` |
| `PIC S9(n) COMP` | `int` or `long` | `PIC S9(09) COMP` | `int signedNum;` |
| `PIC S9(n) COMP-3` | `int`, `long`, or `BigDecimal` | `PIC S9(07)V99 COMP-3` | `BigDecimal amount;` |
| `88-level` | `boolean` | `88 WS-ACTIVE VALUE 'Y'` | `boolean isActive;` |
| `OCCURS n TIMES` | Array or `ArrayList` | `OCCURS 100 TIMES` | `String[] items;` |
| `OCCURS DEPENDING ON` | `ArrayList` | `OCCURS 1 TO 100 DEPENDING ON WS-CT` | `ArrayList<String> items;` |
| `PIC 9(08)` (date) | `LocalDate` | `PIC 9(08)` (YYYYMMDD) | `LocalDate date;` |
| `REDEFINES` | Separate fields or inheritance | see below | see below |

---

## Money Handling — Use BigDecimal, Never double

In COBOL, `PIC 9(7)V99` handles decimal arithmetic perfectly. In Java, `double` has floating-point rounding errors.

```java
// WRONG — double has rounding errors
double a = 0.1 + 0.2;  // Result: 0.30000000000000004 (NOT 0.3)

// CORRECT — BigDecimal for financial calculations
import java.math.BigDecimal;

BigDecimal premium = new BigDecimal("1500.75");
BigDecimal tax = premium.multiply(new BigDecimal("0.18"));
BigDecimal total = premium.add(tax);
// Result: 1500.75 + 270.135 = 1770.885 (exact)
```

**COBOL:**
```cobol
01 WS-PREMIUM      PIC 9(07)V99 VALUE 1500.75.
01 WS-TAX-RATE     PIC V99      VALUE 0.18.
01 WS-TAX          PIC 9(07)V99.
COMPUTE WS-TAX = WS-PREMIUM * WS-TAX-RATE.
```

**Java:**
```java
BigDecimal premium = new BigDecimal("1500.75");
BigDecimal taxRate = new BigDecimal("0.18");
BigDecimal tax = premium.multiply(taxRate);
```

---

## COMP-3 (Packed Decimal) Conversion

COMP-3 stores two digits per byte plus a sign nibble. When converting to Java:

| COBOL | Bytes | Java Type |
|-------|-------|-----------|
| `PIC S9(05) COMP-3` | 3 bytes | `int` |
| `PIC S9(09) COMP-3` | 5 bytes | `int` or `long` |
| `PIC S9(15) COMP-3` | 8 bytes | `long` |
| `PIC S9(07)V99 COMP-3` | 5 bytes | `BigDecimal` |

If the field represents money or has decimal places (V99), always use `BigDecimal`.

---

## Date Handling

COBOL dates are numeric fields. Java uses `LocalDate`.

**COBOL:**
```cobol
01 WS-POLICY-DATE   PIC 9(08).    *> YYYYMMDD format
   MOVE 20240115 TO WS-POLICY-DATE.
```

**Java:**
```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Parsing a COBOL-style date string
String cobolDate = "20240115";
LocalDate policyDate = LocalDate.parse(cobolDate,
    DateTimeFormatter.ofPattern("yyyyMMdd"));

// Common operations
LocalDate today = LocalDate.now();
boolean isExpired = policyDate.isBefore(today);
long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(policyDate, today);
```

---

## 88-Level Condition Names to boolean / enum

**COBOL:**
```cobol
01 WS-POLICY-STATUS    PIC X(01).
   88 WS-ACTIVE        VALUE 'A'.
   88 WS-LAPSED        VALUE 'L'.
   88 WS-CANCELLED     VALUE 'C'.
```

**Java — Simple boolean:**
```java
boolean isActive = status.equals("A");
```

**Java — Enum (preferred for multiple values):**
```java
public enum PolicyStatus {
    ACTIVE("A"), LAPSED("L"), CANCELLED("C");

    private final String code;
    PolicyStatus(String code) { this.code = code; }
    public String getCode() { return code; }

    public static PolicyStatus fromCode(String code) {
        for (PolicyStatus s : values()) {
            if (s.code.equals(code)) return s;
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
```

---

## OCCURS Table to Array / ArrayList

**COBOL (fixed size):**
```cobol
01 WS-POLICY-TABLE.
   05 WS-POLICY-ENTRY OCCURS 100 TIMES.
      10 WS-POL-ID    PIC X(10).
      10 WS-POL-AMT   PIC 9(07)V99.
```

**Java (fixed — Array):**
```java
String[] policyIds = new String[100];
double[] policyAmounts = new double[100];
```

**Java (dynamic — ArrayList, preferred):**
```java
ArrayList<PolicyRecord> policies = new ArrayList<>();
policies.add(new PolicyRecord("POL001", 50000.00));
```

---

## OCCURS DEPENDING ON to ArrayList

**COBOL:**
```cobol
01 WS-RECORD-COUNT     PIC 9(03).
01 WS-POLICY-TABLE.
   05 WS-ENTRY OCCURS 1 TO 999 DEPENDING ON WS-RECORD-COUNT.
      10 WS-POL-ID    PIC X(10).
```

**Java:**
```java
// ArrayList grows automatically — no need to declare max size
ArrayList<String> policyIds = new ArrayList<>();
policyIds.add("POL001");
policyIds.add("POL002");
int recordCount = policyIds.size();  // equivalent of WS-RECORD-COUNT
```

---

## Group-Level Items to Nested POJOs

**COBOL:**
```cobol
01 WS-POLICY-RECORD.
   05 WS-POLICY-ID       PIC X(10).
   05 WS-INSURED-INFO.
      10 WS-FIRST-NAME   PIC X(20).
      10 WS-LAST-NAME    PIC X(20).
      10 WS-DOB          PIC 9(08).
   05 WS-COVERAGE-INFO.
      10 WS-SUM-ASSURED  PIC 9(09)V99.
      10 WS-PLAN-CODE    PIC X(04).
```

**Java:**
```java
public class PolicyRecord {
    private String policyId;
    private InsuredInfo insuredInfo;   // Nested POJO for group item
    private CoverageInfo coverageInfo; // Nested POJO for group item
}

public class InsuredInfo {
    private String firstName;
    private String lastName;
    private LocalDate dob;
}

public class CoverageInfo {
    private BigDecimal sumAssured;
    private String planCode;
}
```

---

## REDEFINES — No Direct Java Equivalent

**COBOL:**
```cobol
01 WS-DATE-FIELD        PIC 9(08).
01 WS-DATE-PARTS REDEFINES WS-DATE-FIELD.
   05 WS-YEAR           PIC 9(04).
   05 WS-MONTH          PIC 9(02).
   05 WS-DAY            PIC 9(02).
```

**Java options:**

1. Separate fields with a parsing method:
```java
String dateField = "20240115";
int year  = Integer.parseInt(dateField.substring(0, 4));
int month = Integer.parseInt(dateField.substring(4, 6));
int day   = Integer.parseInt(dateField.substring(6, 8));
```

2. Use `LocalDate` (preferred):
```java
LocalDate date = LocalDate.parse("20240115",
    DateTimeFormatter.ofPattern("yyyyMMdd"));
int year  = date.getYear();
int month = date.getMonthValue();
int day   = date.getDayOfMonth();
```

3. For type-variant REDEFINES (same area, different interpretations based on a type flag), use inheritance:
```java
abstract class Transaction { /* common fields */ }
class PremiumTransaction extends Transaction { /* premium-specific */ }
class LoanTransaction extends Transaction { /* loan-specific */ }
```
