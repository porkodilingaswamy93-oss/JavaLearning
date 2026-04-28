# Phase 7: Repository (VSAM I/O to Spring Data JPA)

---

## COBOL VSAM Operations to JPA Methods

| COBOL VSAM Operation | Spring Data JPA | SQL Generated |
|---------------------|----------------|---------------|
| `READ file KEY IS key` | `findById("POL001")` | `SELECT * FROM POLICY WHERE POLICY_ID = ?` |
| `WRITE file FROM record` | `save(policy)` | `INSERT INTO POLICY ...` |
| `REWRITE file FROM record` | `save(policy)` (existing) | `UPDATE POLICY SET ... WHERE POLICY_ID = ?` |
| `DELETE file` | `deleteById("POL001")` | `DELETE FROM POLICY WHERE POLICY_ID = ?` |
| `READ file NEXT` (sequential) | `findAll()` | `SELECT * FROM POLICY` |
| `START file KEY >= key` | `findByPolicyIdGreaterThan("POL001")` | `SELECT * WHERE POLICY_ID > ?` |

## Repository Interface

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, String> {

    // Spring auto-generates these methods — you don't write SQL!
    // findAll()              = READ all records (sequential read)
    // findById("POL001")     = READ by key (keyed read)
    // save(policy)           = WRITE or REWRITE
    // deleteById("POL001")   = DELETE

    // Custom queries — Spring generates SQL from the method name:

    List<Policy> findByStatus(String status);
    // Generated: SELECT * FROM POLICY WHERE STATUS = ?

    List<Policy> findByInsuredNameContaining(String name);
    // Generated: SELECT * FROM POLICY WHERE INSURED_NAME LIKE '%name%'

    List<Policy> findBySumAssuredGreaterThan(double amount);
    // Generated: SELECT * FROM POLICY WHERE SUM_ASSURED > ?

    List<Policy> findByStatusOrderBySumAssuredDesc(String status);
    // Generated: SELECT * FROM POLICY WHERE STATUS = ? ORDER BY SUM_ASSURED DESC
}
```

## How It Works

1. You define an **interface** (not a class) that extends `JpaRepository`
2. `JpaRepository<Policy, String>` means: Entity type is `Policy`, primary key type is `String`
3. Spring automatically provides implementations for standard CRUD operations
4. For custom queries, just define a method name following Spring's naming convention
5. Spring reads the method name and generates the correct SQL

## Key Points

- You never write SQL manually (Spring generates it)
- Method names follow a pattern: `findBy` + field name + condition
- This is like VSAM access but without writing READ/WRITE/DELETE paragraphs
- The Repository layer replaces your COBOL I/O paragraphs entirely
