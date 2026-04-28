# Phase 7: CICS Transaction Flow vs Spring Boot

---

## CICS Flow

```
User types INQP on 3270 terminal
    -> CICS PCT finds program for INQP
        -> COBOL program receives COMMAREA
            -> Program reads VSAM file by key
        <- Program populates COMMAREA with result
    <- CICS SEND MAP to 3270 screen
User sees policy details on screen
```

## Spring Boot Flow

```
Client sends GET /api/policies/POL001
    -> Controller receives HTTP request
        -> Service contains business logic
            -> Repository reads from database
        <- Returns Policy object
    <- Spring converts to JSON automatically
Client receives JSON response
```

---

## CICS Transaction Table = REST Endpoints

| CICS Transaction | CICS Program | REST Endpoint | HTTP Method |
|-----------------|-------------|---------------|-------------|
| INQP (Inquiry) | POLICY-INQUIRY-PGM | `/api/policies/{id}` | GET |
| ADDP (Add) | POLICY-CREATE-PGM | `/api/policies` | POST |
| UPDP (Update) | POLICY-UPDATE-PGM | `/api/policies/{id}` | PUT |
| DELP (Delete) | POLICY-DELETE-PGM | `/api/policies/{id}` | DELETE |
| LSTP (List) | POLICY-LIST-PGM | `/api/policies` | GET |

---

## COMMAREA = JSON

**CICS COMMAREA (input — like RECEIVE MAP):**
```cobol
01 DFHCOMMAREA.
   05 CA-POLICY-ID       PIC X(10).
   05 CA-INSURED-NAME    PIC X(30).
   05 CA-SUM-ASSURED     PIC 9(09)V99.
   05 CA-STATUS          PIC X(10).
```

**JSON Request (POST /api/policies):**
```json
{
    "policyId": "POL005",
    "insuredName": "NEW CUSTOMER",
    "sumAssured": 75000.00,
    "status": "ACTIVE"
}
```

**JSON Response (GET /api/policies/POL001):**
```json
{
    "policyId": "POL001",
    "insuredName": "JOHN SMITH",
    "sumAssured": 50000.00,
    "status": "ACTIVE"
}
```

---

## BMS Map = JSON Format

| BMS Map (3270 Screen) | Spring Boot |
|-----------------------|-------------|
| SEND MAP (display data) | Return JSON response |
| RECEIVE MAP (accept input) | Accept JSON request body |
| Screen fields | JSON key-value pairs |
| MDT (Modified Data Tag) | Changed fields in JSON request |
| ERRMSG field on screen | Error response JSON with message |

---

## Side-by-Side: Inquiry Transaction

**COBOL/CICS (INQP):**
```cobol
PROCEDURE DIVISION.
    EXEC CICS RECEIVE MAP('INQMAP') MAPSET('INQSET')
        INTO(DFHCOMMAREA)
    END-EXEC.

    MOVE CA-POLICY-ID TO WS-POLICY-KEY.
    READ POLICY-FILE INTO WS-POLICY-REC
        KEY IS WS-POLICY-KEY
        INVALID KEY
            MOVE 'POLICY NOT FOUND' TO CA-MSG
        NOT INVALID KEY
            MOVE WS-INSURED-NAME TO CA-INSURED-NAME
            MOVE WS-SUM-ASSURED TO CA-SUM-ASSURED
    END-READ.

    EXEC CICS SEND MAP('INQMAP') MAPSET('INQSET')
        FROM(DFHCOMMAREA)
    END-EXEC.
```

**Spring Boot (GET /api/policies/{id}):**
```java
@GetMapping("/{id}")
public Policy getPolicy(@PathVariable String id) {
    return policyService.getPolicyById(id);
    // Spring automatically converts to JSON
}
```
