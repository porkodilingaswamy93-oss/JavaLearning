# Phase 5: COBOL Equivalents for Java Collections

Side-by-side COBOL and Java for collections.

---

## 1. OCCURS Table = ArrayList

**COBOL (fixed-size table):**
```cobol
WORKING-STORAGE SECTION.
01 WS-POLICY-TABLE.
   05 WS-POLICY-ENTRY OCCURS 100 TIMES.
      10 WS-POL-ID    PIC X(10).
01 WS-ENTRY-COUNT     PIC 9(03) VALUE 0.

PROCEDURE DIVISION.
    MOVE 'POL001' TO WS-POL-ID(1).
    MOVE 'POL002' TO WS-POL-ID(2).
    MOVE 'POL003' TO WS-POL-ID(3).
    MOVE 3 TO WS-ENTRY-COUNT.

    PERFORM VARYING WS-IDX FROM 1 BY 1
        UNTIL WS-IDX > WS-ENTRY-COUNT
        DISPLAY WS-POL-ID(WS-IDX)
    END-PERFORM.
```

**Java (dynamic-size ArrayList):**
```java
ArrayList<String> policyList = new ArrayList<>();  // No fixed size
policyList.add("POL001");
policyList.add("POL002");
policyList.add("POL003");

System.out.println("Count: " + policyList.size());  // 3

for (String polId : policyList) {
    System.out.println(polId);
}
```

---

## 2. VSAM KSDS = HashMap

**COBOL (keyed file access):**
```cobol
* Write a record to VSAM:
MOVE 'POL001' TO WS-POLICY-KEY.
MOVE policy-data TO WS-POLICY-RECORD.
WRITE POLICY-FILE FROM WS-POLICY-RECORD.

* Read a record by key:
MOVE 'POL002' TO WS-POLICY-KEY.
READ POLICY-FILE INTO WS-POLICY-RECORD
    KEY IS WS-POLICY-KEY
    INVALID KEY DISPLAY 'NOT FOUND'
END-READ.

* Delete a record:
DELETE POLICY-FILE.
```

**Java (HashMap):**
```java
HashMap<String, PolicyData> policyMap = new HashMap<>();

// Write (PUT)
policyMap.put("POL001", new PolicyData("POL001", "JOHN", 50000));

// Read by key (GET)
PolicyData found = policyMap.get("POL002");
if (found != null) {
    System.out.println("Found: " + found.getName());
} else {
    System.out.println("NOT FOUND");
}

// Delete (REMOVE)
policyMap.remove("POL001");
```

---

## 3. SORT Utility = Collections.sort()

**JCL SORT (ascending by name):**
```
//SORT EXEC PGM=SORT
//SORTIN   DD DSN=INPUT.FILE
//SORTOUT  DD DSN=OUTPUT.FILE
//SYSIN    DD *
  SORT FIELDS=(1,30,CH,A)
/*
```

**Java:**
```java
ArrayList<String> names = new ArrayList<>();
names.add("WILSON");
names.add("SMITH");
names.add("TAYLOR");
Collections.sort(names);  // Ascending: [DOE, SMITH, TAYLOR, WILSON]
```

**JCL SORT (descending by numeric amount):**
```
  SORT FIELDS=(31,9,ZD,D)
```

**Java (sort objects by field descending):**
```java
Collections.sort(policies, (p1, p2) ->
    Double.compare(p2.getSumAssured(), p1.getSumAssured()));
```

---

## 4. SORT with EQUALS DELETE = HashSet

**JCL SORT (remove duplicates):**
```
//SYSIN DD *
  SORT FIELDS=(1,10,CH,A)
  SUM FIELDS=NONE
/*
```

**Java (HashSet automatically rejects duplicates):**
```java
HashSet<String> uniqueAgents = new HashSet<>();
uniqueAgents.add("AGENT001");
uniqueAgents.add("AGENT002");
uniqueAgents.add("AGENT001");  // Rejected — duplicate
// Size: 2 (not 3)
```

---

## 5. Sequential File = LinkedHashMap

**COBOL (sequential read maintains order):**
```cobol
OPEN INPUT TXN-FILE.
PERFORM UNTIL END-OF-FILE
    READ TXN-FILE INTO WS-TXN-RECORD
        AT END SET END-OF-FILE TO TRUE
    END-READ
    DISPLAY WS-TXN-ID ' -> ' WS-TXN-DESC
END-PERFORM.
```

**Java (LinkedHashMap maintains insertion order):**
```java
LinkedHashMap<String, String> transactionLog = new LinkedHashMap<>();
transactionLog.put("TXN001", "PREMIUM PAYMENT");
transactionLog.put("TXN002", "LOAN DISBURSEMENT");
transactionLog.put("TXN003", "REINSTATEMENT");

for (Map.Entry<String, String> entry : transactionLog.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
// Prints in insertion order (unlike regular HashMap)
```

---

## Summary

| COBOL/Mainframe | Java Collection | Use When |
|----------------|----------------|----------|
| OCCURS n TIMES | `ArrayList<T>` | Ordered list of items |
| VSAM KSDS | `HashMap<K,V>` | Fast lookup by key |
| VSAM KSDS sorted | `TreeMap<K,V>` | Sorted lookup by key |
| Sequential file | `LinkedHashMap<K,V>` | Keyed + insertion order |
| SORT FIELDS=(key,A) | `Collections.sort()` | Sort a list |
| SORT + SUM FIELDS=NONE | `HashSet<T>` | Unique values only |
