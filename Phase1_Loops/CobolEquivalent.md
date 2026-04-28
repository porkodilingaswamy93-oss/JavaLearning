# Phase 1: COBOL Equivalents for Java Loops

Side-by-side COBOL and Java for every loop type.

---

## 1. PERFORM VARYING = for loop

**COBOL:**
```cobol
WORKING-STORAGE SECTION.
01 WS-INDEX        PIC 9(03).

PROCEDURE DIVISION.
    PERFORM VARYING WS-INDEX FROM 1 BY 1
        UNTIL WS-INDEX > 5
        DISPLAY 'Policy Number: ' WS-INDEX
    END-PERFORM.
    STOP RUN.
```

**Java:**
```java
for (int index = 1; index <= 5; index++) {
    System.out.println("Policy Number: " + index);
}
```

---

## 2. PERFORM UNTIL (TEST BEFORE) = while loop

**COBOL:**
```cobol
WORKING-STORAGE SECTION.
01 WS-EOF-FLAG     PIC X(01) VALUE 'N'.
   88 END-OF-FILE  VALUE 'Y'.
01 WS-RECORD-COUNT PIC 9(05) VALUE 0.

PROCEDURE DIVISION.
    MOVE 'N' TO WS-EOF-FLAG.
    PERFORM UNTIL END-OF-FILE
        READ POLICY-FILE INTO WS-POLICY-REC
            AT END
                SET END-OF-FILE TO TRUE
            NOT AT END
                ADD 1 TO WS-RECORD-COUNT
                PERFORM PROCESS-POLICY
        END-READ
    END-PERFORM.
    DISPLAY 'Total processed: ' WS-RECORD-COUNT.
    STOP RUN.
```

**Java:**
```java
int recordCount = 0;
int totalRecords = 3;
while (recordCount < totalRecords) {
    recordCount++;
    System.out.println("Processing policy record: " + recordCount);
}
System.out.println("End of file reached. Total processed: " + recordCount);
```

---

## 3. PERFORM WITH TEST AFTER = do-while loop

**COBOL:**
```cobol
WORKING-STORAGE SECTION.
01 WS-RESPONSE     PIC X(01).
01 WS-ATTEMPT      PIC 9(02) VALUE 0.

PROCEDURE DIVISION.
    PERFORM WITH TEST AFTER
        UNTIL WS-ATTEMPT >= 3
        ADD 1 TO WS-ATTEMPT
        DISPLAY 'Login attempt: ' WS-ATTEMPT
    END-PERFORM.
    DISPLAY 'Max attempts reached.'.
    STOP RUN.
```

**Java:**
```java
int attempt = 1;
do {
    System.out.println("Login attempt: " + attempt);
    attempt++;
} while (attempt <= 3);
System.out.println("Max attempts reached.");
```

The key: `do-while` always executes the body at least once, then checks the condition. Same as `TEST AFTER` in COBOL.

---

## 4. Nested PERFORM = Nested for loops

**COBOL:**
```cobol
WORKING-STORAGE SECTION.
01 WS-AGENT        PIC 9(02).
01 WS-POLICY       PIC 9(02).

PROCEDURE DIVISION.
    PERFORM VARYING WS-AGENT FROM 1 BY 1
        UNTIL WS-AGENT > 2
        PERFORM VARYING WS-POLICY FROM 1 BY 1
            UNTIL WS-POLICY > 3
            DISPLAY 'Agent: ' WS-AGENT ' Policy: ' WS-POLICY
        END-PERFORM
    END-PERFORM.
    STOP RUN.
```

**Java:**
```java
for (int agent = 1; agent <= 2; agent++) {
    for (int policy = 1; policy <= 3; policy++) {
        System.out.println("Agent: " + agent + " Policy: " + policy);
    }
}
```

---

## 5. OCCURS Table Iteration = Enhanced for-each

**COBOL:**
```cobol
WORKING-STORAGE SECTION.
01 WS-POLICY-TABLE.
   05 WS-POLICY-ID OCCURS 4 TIMES PIC X(10).
01 WS-IDX          PIC 9(02).

PROCEDURE DIVISION.
    MOVE 'POL001' TO WS-POLICY-ID(1).
    MOVE 'POL002' TO WS-POLICY-ID(2).
    MOVE 'POL003' TO WS-POLICY-ID(3).
    MOVE 'POL004' TO WS-POLICY-ID(4).

    PERFORM VARYING WS-IDX FROM 1 BY 1
        UNTIL WS-IDX > 4
        DISPLAY 'Policy: ' WS-POLICY-ID(WS-IDX)
    END-PERFORM.
```

**Java:**
```java
String[] policyIds = {"POL001", "POL002", "POL003", "POL004"};
for (String policyId : policyIds) {
    System.out.println("Policy: " + policyId);
}
```

Note: COBOL OCCURS arrays start at index 1. Java arrays start at index 0.

---

## 6. GO TO / EXIT PERFORM = break / continue

**COBOL (EXIT PERFORM equivalent):**
```cobol
    PERFORM VARYING WS-IDX FROM 1 BY 1
        UNTIL WS-IDX > 4
        IF WS-STATUS(WS-IDX) = 'LAPSED'
            DISPLAY 'Found LAPSED — stopping.'
            EXIT PERFORM
        END-IF
        DISPLAY 'Processing: ' WS-STATUS(WS-IDX)
    END-PERFORM.
```

**Java (break):**
```java
String[] statuses = {"ACTIVE", "ACTIVE", "LAPSED", "ACTIVE"};
for (String status : statuses) {
    if (status.equals("LAPSED")) {
        System.out.println("Found LAPSED — stopping.");
        break;
    }
    System.out.println("Processing: " + status);
}
```

**COBOL (NEXT SENTENCE / skip equivalent):**
```cobol
    PERFORM VARYING WS-IDX FROM 1 BY 1
        UNTIL WS-IDX > 5
        IF WS-PREMIUM(WS-IDX) = 0
            CONTINUE
        ELSE
            DISPLAY 'Premium: ' WS-PREMIUM(WS-IDX)
        END-IF
    END-PERFORM.
```

**Java (continue):**
```java
int[] premiums = {500, 0, 1200, 0, 800};
for (int premium : premiums) {
    if (premium == 0) {
        continue;
    }
    System.out.println("Premium to process: $" + premium);
}
```
