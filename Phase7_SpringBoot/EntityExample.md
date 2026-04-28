# Phase 7: Entity Class (Copybook to @Entity)

---

## COBOL Copybook

```cobol
01 WS-POLICY-RECORD.
   05 WS-POLICY-ID       PIC X(10).
   05 WS-INSURED-NAME    PIC X(30).
   05 WS-SUM-ASSURED     PIC 9(09)V99.
   05 WS-STATUS          PIC X(10).
```

## Java @Entity Class

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity                          // Tells Spring: this maps to a database table
@Table(name = "POLICY")          // Table name (like VSAM dataset name)
public class Policy {

    @Id                           // Primary key (like VSAM KSDS key)
    @Column(name = "POLICY_ID")
    private String policyId;      // PIC X(10) -> String

    @Column(name = "INSURED_NAME")
    private String insuredName;   // PIC X(30) -> String

    @Column(name = "SUM_ASSURED")
    private double sumAssured;    // PIC 9(09)V99 -> double

    @Column(name = "STATUS")
    private String status;        // PIC X(10) -> String

    // Default constructor (required by JPA)
    public Policy() {}

    // Parameterized constructor
    public Policy(String policyId, String insuredName,
                  double sumAssured, String status) {
        this.policyId = policyId;
        this.insuredName = insuredName;
        this.sumAssured = sumAssured;
        this.status = status;
    }

    // Getters and Setters (same as Phase 3 POJO)
    public String getPolicyId()     { return policyId; }
    public String getInsuredName()  { return insuredName; }
    public double getSumAssured()   { return sumAssured; }
    public String getStatus()       { return status; }

    public void setPolicyId(String policyId)        { this.policyId = policyId; }
    public void setInsuredName(String insuredName)   { this.insuredName = insuredName; }
    public void setSumAssured(double sumAssured)     { this.sumAssured = sumAssured; }
    public void setStatus(String status)             { this.status = status; }
}
```

## Annotation Breakdown

| Annotation | Purpose | COBOL Equivalent |
|-----------|---------|-----------------|
| `@Entity` | Marks this class as a database table mapping | Copybook used as file record layout |
| `@Table(name = "POLICY")` | Specifies the table name | VSAM dataset name / DB2 table name |
| `@Id` | Marks the primary key field | VSAM KSDS primary key |
| `@Column(name = "POLICY_ID")` | Maps field to a specific column name | Copybook field at specific offset |

## Key Points

- An Entity class is just a POJO (Phase 3) with database annotations added
- `@Id` marks which field is the primary key (like VSAM KSDS key)
- `@Column` maps Java field names to database column names
- JPA (Java Persistence API) handles all SQL generation automatically
- For money fields, use `BigDecimal` instead of `double` in production
