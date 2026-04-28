# Phase 3: COBOL Equivalents for POJO Classes

Actual copybook samples alongside their Java POJO equivalents.

---

## 1. Policy Record Copybook (POLREC.CPY) to POJO

**COBOL Copybook:**
```cobol
      * POLREC.CPY - Policy Record Layout
       01 WS-POLICY-RECORD.
          05 WS-POLICY-ID       PIC X(10).
          05 WS-INSURED-NAME    PIC X(30).
          05 WS-POLICY-STATUS   PIC X(10).
          05 WS-SUM-ASSURED     PIC 9(09)V99.
          05 WS-PLAN-CODE       PIC X(04).
          05 WS-TERM-YEARS      PIC 9(02).
```

**Java POJO:**
```java
public class PolicyRecord {
    private String policyId;       // PIC X(10)
    private String insuredName;    // PIC X(30)
    private String policyStatus;   // PIC X(10)
    private double sumAssured;     // PIC 9(09)V99
    private String planCode;       // PIC X(04)
    private int termYears;         // PIC 9(02)

    // Default constructor = INITIALIZE
    public PolicyRecord() {}

    // Getters = DISPLAY field
    public String getPolicyId() { return policyId; }
    // ... getter for each field

    // Setters = MOVE value TO field
    public void setPolicyId(String policyId) { this.policyId = policyId; }
    // ... setter for each field
}
```

---

## 2. Payment Record Copybook (PAYREC.CPY) to POJO

**COBOL Copybook:**
```cobol
      * PAYREC.CPY - Payment Record Layout (OneInc/Transamerica)
       01 WS-PAYMENT-RECORD.
          05 WS-POLICY-NUMBER      PIC X(10).
          05 WS-TXN-TYPE           PIC X(15).
             88 WS-PREMIUM         VALUE 'PREMIUM'.
             88 WS-LOAN            VALUE 'LOAN'.
             88 WS-REINSTATEMENT   VALUE 'REINSTATEMENT'.
          05 WS-PAY-AMOUNT         PIC 9(09)V99.
          05 WS-PAY-METHOD         PIC X(10).
             88 WS-EFT             VALUE 'EFT'.
             88 WS-CHECK           VALUE 'CHECK'.
             88 WS-CREDIT-CARD     VALUE 'CREDIT_CARD'.
          05 WS-PROC-STATUS        PIC X(10).
             88 WS-PENDING         VALUE 'PENDING'.
             88 WS-COMPLETED       VALUE 'COMPLETED'.
             88 WS-FAILED          VALUE 'FAILED'.
```

**Java POJO:**
```java
public class PaymentRecord {
    private String policyNumber;       // PIC X(10)
    private String transactionType;    // PREMIUM, LOAN, REINSTATEMENT
    private double paymentAmount;      // PIC 9(09)V99
    private String paymentMethod;      // EFT, CHECK, CREDIT_CARD
    private String processingStatus;   // PENDING, COMPLETED, FAILED

    // Getters, Setters, Constructors...

    // Business method (like a PERFORM paragraph)
    public void processPayment() {
        System.out.println("Processing " + transactionType
            + " payment of $" + paymentAmount);
        this.processingStatus = "COMPLETED";
    }
}
```

---

## 3. Operation Mapping

**MOVE = Setter:**
```cobol
MOVE 'POL001'     TO WS-POLICY-ID.
MOVE 'JOHN SMITH' TO WS-INSURED-NAME.
MOVE 50000.00     TO WS-SUM-ASSURED.
```
```java
policy.setPolicyId("POL001");
policy.setInsuredName("JOHN SMITH");
policy.setSumAssured(50000.00);
```

**DISPLAY = Getter:**
```cobol
DISPLAY 'ID: ' WS-POLICY-ID.
DISPLAY 'Name: ' WS-INSURED-NAME.
DISPLAY 'Sum: ' WS-SUM-ASSURED.
```
```java
System.out.println("ID: " + policy.getPolicyId());
System.out.println("Name: " + policy.getInsuredName());
System.out.println("Sum: " + policy.getSumAssured());
```

**INITIALIZE = Default Constructor:**
```cobol
INITIALIZE WS-POLICY-RECORD.
```
```java
PolicyRecord policy = new PolicyRecord();
// Fields get default values: null for String, 0 for numbers
```

**COPY statement = Import/Use POJO:**
```cobol
COPY POLREC.
*> Now WS-POLICY-RECORD is available in this program.
```
```java
// PolicyRecord class is available when compiled together
PolicyRecord policy = new PolicyRecord();
```

---

## 4. Batch Processing Pattern

**COBOL:**
```cobol
PROCEDURE DIVISION.
MAIN-PROCESS.
    OPEN INPUT POLICY-FILE.
    PERFORM UNTIL END-OF-FILE
        READ POLICY-FILE INTO WS-POLICY-RECORD
            AT END SET END-OF-FILE TO TRUE
            NOT AT END PERFORM PROCESS-RECORD
        END-READ
    END-PERFORM.
    PERFORM PRINT-SUMMARY.
    CLOSE POLICY-FILE.
    STOP RUN.

PROCESS-RECORD.
    IF WS-POLICY-STATUS = 'ACTIVE'
        ADD 1 TO WS-ACTIVE-COUNT
        ADD WS-SUM-ASSURED TO WS-TOTAL-SUM
    END-IF.
```

**Java:**
```java
PolicyRecord[] policies = { /* loaded records */ };

int activeCount = 0;
double totalSum = 0;

for (PolicyRecord pol : policies) {
    if (pol.getPolicyStatus().equals("ACTIVE")) {
        activeCount++;
        totalSum += pol.getSumAssured();
    }
}
System.out.println("Active: " + activeCount + " Total: $" + totalSum);
```
