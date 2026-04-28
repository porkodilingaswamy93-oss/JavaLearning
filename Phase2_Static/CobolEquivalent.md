# Phase 2: COBOL Equivalents for Static Keyword

Side-by-side COBOL and Java for static concepts.

---

## 1. WORKING-STORAGE Shared Field = Static Variable

**COBOL (CICS context):**
```cobol
WORKING-STORAGE SECTION.
*> One copy shared across all CICS tasks using this program
01 WS-POLICY-COUNT     PIC 9(05) VALUE 0.

PROCEDURE DIVISION.
    ADD 1 TO WS-POLICY-COUNT.
    DISPLAY 'Total policies: ' WS-POLICY-COUNT.
```

**Java:**
```java
public class PolicyProcessor {
    // One copy shared across ALL objects of this class
    static int totalPoliciesProcessed = 0;

    public PolicyProcessor() {
        totalPoliciesProcessed++;  // Every new object increments the shared counter
    }
}
```

---

## 2. LOCAL-STORAGE Per-Task Field = Instance Variable

**COBOL (CICS context):**
```cobol
LOCAL-STORAGE SECTION.
*> Each CICS task gets its own copy
01 LS-POLICY-ID       PIC X(10).
01 LS-POLICY-STATUS   PIC X(10).
```

**Java:**
```java
public class PolicyProcessor {
    // Each object gets its own copy
    String policyId;
    String policyStatus;
}
```

---

## 3. 78-Level / VALUE Constant = Static Final

**COBOL:**
```cobol
WORKING-STORAGE SECTION.
78 WS-MAX-POLICIES        VALUE 1000.
01 WS-COMPANY-NAME    PIC X(20) VALUE 'TRANSAMERICA'.
01 WS-TAX-RATE        PIC V99   VALUE 0.18.

*> These values never change during execution.
```

**Java:**
```java
static final int MAX_POLICIES = 1000;
static final String COMPANY_NAME = "TRANSAMERICA";
static final double TAX_RATE = 0.18;

// Attempting to change: COMPANY_NAME = "OTHER";  // COMPILE ERROR
```

---

## 4. Utility COPY Paragraph = Static Method

**COBOL:**
```cobol
*> In a COPY member TAXCALC.CPY, usable by any program:
CALCULATE-TAX.
    COMPUTE WS-TAX = WS-PREMIUM * 0.18.
    EXIT.

*> Any program can:
    COPY TAXCALC.
    MOVE 5000.00 TO WS-PREMIUM.
    PERFORM CALCULATE-TAX.
    DISPLAY 'Tax: ' WS-TAX.
```

**Java:**
```java
// Static method — no object needed, callable from anywhere
static double calculateTax(double premiumAmount) {
    return premiumAmount * 0.18;
}

// Called using class name:
double tax = TaxCalculator.calculateTax(5000.00);
```

---

## 5. Batch Job Initialization = Static Block

**COBOL:**
```cobol
PROCEDURE DIVISION.
MAIN-PARAGRAPH.
    *> One-time initialization at start of batch job
    PERFORM INITIALIZE-ENVIRONMENT.
    PERFORM PROCESS-RECORDS.
    STOP RUN.

INITIALIZE-ENVIRONMENT.
    MOVE 'PRODUCTION' TO WS-ENVIRONMENT.
    OPEN INPUT POLICY-FILE.
    DISPLAY '>>> System initialized as: ' WS-ENVIRONMENT.
```

**Java:**
```java
static String systemEnvironment;

// Runs once when the class is first loaded into memory
static {
    systemEnvironment = "PRODUCTION";
    System.out.println(">>> System initialized as: " + systemEnvironment);
}
```

---

## Summary Table

| Concept | COBOL | Java |
|---------|-------|------|
| Shared data (one copy) | WORKING-STORAGE field | `static int count;` |
| Per-instance data | LOCAL-STORAGE field | `String name;` |
| Constant | 78-level / VALUE clause | `static final int MAX = 1000;` |
| Utility function | COPY member paragraph | `static double calc(double x)` |
| One-time init | First PERFORM in PROCEDURE DIVISION | `static { ... }` block |
