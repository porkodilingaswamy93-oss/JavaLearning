# Phase 4: COBOL Equivalents for Inheritance and OOP

Side-by-side COBOL and Java for OOP concepts.

---

## 1. Base Copybook = Parent Class

**COBOL (POLICY-REC.CPY — base copybook used by many programs):**
```cobol
      * POLICY-REC.CPY - Base policy record layout
       01 WS-POLICY-RECORD.
          05 WS-POLICY-ID       PIC X(10).
          05 WS-INSURED-NAME    PIC X(30).
          05 WS-SUM-ASSURED     PIC 9(09)V99.
```

**Java (Parent class):**
```java
class Policy {
    private String policyId;
    private String insuredName;
    private double sumAssured;

    // Getters, setters, constructors...

    public void displayDetails() {
        System.out.println("Policy ID: " + policyId);
        System.out.println("Insured: " + insuredName);
        System.out.println("Sum Assured: $" + sumAssured);
    }
}
```

---

## 2. Extended Copybook = Child Class

**COBOL (Life policy adds fields to base):**
```cobol
       COPY POLICY-REC.
      * Additional fields for life insurance:
          05 WS-NOMINEE         PIC X(30).
          05 WS-RIDER-TYPE      PIC X(20).
```

**Java:**
```java
class LifePolicy extends Policy {
    private String nominee;
    private String riderType;

    // Inherits policyId, insuredName, sumAssured from Policy
    // Adds its own nominee and riderType
}
```

---

## 3. COPY ... REPLACING = Method Overriding

**COBOL (Each program calculates premium differently):**
```cobol
      * In LIFE-PROGRAM:
       CALCULATE-PREMIUM.
           COMPUTE WS-PREMIUM = WS-SUM-ASSURED * 0.05.

      * In AUTO-PROGRAM:
       CALCULATE-PREMIUM.
           COMPUTE WS-PREMIUM = WS-SUM-ASSURED * 0.05.
```

Both programs have the same paragraph name but different logic. In Java, this is method overriding:

**Java:**
```java
class Policy {
    public double calculatePremium() {
        return sumAssured * 0.03;  // Default 3%
    }
}

class LifePolicy extends Policy {
    @Override
    public double calculatePremium() {
        return getSumAssured() * 0.05;  // Life = 5%
    }
}

class AutoPolicy extends Policy {
    @Override
    public double calculatePremium() {
        return getSumAssured() * 0.05;  // Auto = 5%
    }
}
```

---

## 4. Different Programs, Same COMMAREA = Polymorphism

**COBOL:**
```cobol
      * A batch job reads records and CALLs different programs
      * based on policy type, but all use the same COMMAREA structure:
       EVALUATE WS-POLICY-TYPE
           WHEN 'LIFE' CALL 'LIFEPROC' USING WS-POLICY-RECORD
           WHEN 'AUTO' CALL 'AUTOPROC' USING WS-POLICY-RECORD
       END-EVALUATE.

      * Each program processes the same record differently.
```

**Java (Polymorphism — Java picks the right method automatically):**
```java
Policy[] policies = new Policy[3];
policies[0] = new LifePolicy(...);   // Stored as Policy
policies[1] = new AutoPolicy(...);   // Stored as Policy
policies[2] = new LifePolicy(...);   // Stored as Policy

for (Policy pol : policies) {
    // Java automatically calls the correct calculatePremium()
    // based on the actual object type (Life or Auto)
    System.out.println(pol.calculatePremium());
}
```

---

## 5. CALL Interface Specification = Interface

**COBOL:**
```cobol
      * When you CALL a subprogram, the USING clause defines the contract:
       CALL 'AUDITLOG' USING WS-AUDIT-RECORD.
      * The called program MUST accept that parameter structure.
```

**Java:**
```java
interface Auditable {
    String getAuditTrail();  // Any class implementing this MUST define this method
}

class PremiumTransaction implements Auditable {
    @Override
    public String getAuditTrail() {
        return "PREMIUM on " + policyNumber + " for $" + amount;
    }
}
```

---

## Summary

| COBOL Pattern | Java OOP Concept |
|---------------|-----------------|
| Base copybook included by all programs | Parent class |
| COPY base + add extra fields | `extends` (child class) |
| Same paragraph name, different logic per program | `@Override` (method overriding) |
| Same COMMAREA, different programs process differently | Polymorphism |
| CALL spec defining required parameters | `interface` |
| Program that defines structure but not all logic | `abstract class` |
| `super` | Calling parent's paragraph before adding child logic |
