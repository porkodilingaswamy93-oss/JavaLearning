# Phase 7: Service (PERFORM Paragraphs to @Service Methods)

---

This is where your COBOL business logic lives in Java. Each method is like a PERFORM paragraph.

## Service Class

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyService {

    @Autowired   // Spring injects the repository (like CICS LINK to subprogram)
    private PolicyRepository policyRepository;

    // Like: PERFORM GET-ALL-POLICIES
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    // Like: PERFORM GET-POLICY-BY-ID
    //       MOVE WS-INPUT-KEY TO WS-POLICY-KEY
    //       READ POLICY-FILE INTO WS-POLICY-REC KEY IS WS-POLICY-KEY
    public Policy getPolicyById(String policyId) {
        return policyRepository.findById(policyId)
            .orElseThrow(() -> new RuntimeException("Policy not found: " + policyId));
        // orElseThrow = like checking FILE STATUS after READ
        //   FILE STATUS '23' = record not found -> throw error
    }

    // Like: PERFORM CREATE-POLICY
    //       MOVE input fields TO WS-POLICY-REC
    //       WRITE POLICY-FILE FROM WS-POLICY-REC
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    // Like: PERFORM UPDATE-POLICY
    //       READ POLICY-FILE FOR UPDATE
    //       MOVE new values TO WS-POLICY-REC
    //       REWRITE POLICY-FILE FROM WS-POLICY-REC
    public Policy updatePolicy(String policyId, Policy updatedPolicy) {
        Policy existing = getPolicyById(policyId);
        existing.setInsuredName(updatedPolicy.getInsuredName());
        existing.setSumAssured(updatedPolicy.getSumAssured());
        existing.setStatus(updatedPolicy.getStatus());
        return policyRepository.save(existing);
    }

    // Like: PERFORM DELETE-POLICY
    //       DELETE POLICY-FILE
    public void deletePolicy(String policyId) {
        policyRepository.deleteById(policyId);
    }

    // Like: PERFORM GET-ACTIVE-POLICIES
    //       (using SORT INCLUDE COND or SEARCH logic)
    public List<Policy> getActivePolicies() {
        return policyRepository.findByStatus("ACTIVE");
    }
}
```

## COBOL Paragraph to Java Method Mapping

| COBOL Paragraph | Java Service Method |
|----------------|-------------------|
| `PERFORM GET-ALL-POLICIES` | `getAllPolicies()` |
| `PERFORM GET-POLICY-BY-ID` | `getPolicyById(id)` |
| `PERFORM CREATE-POLICY` | `createPolicy(policy)` |
| `PERFORM UPDATE-POLICY` | `updatePolicy(id, policy)` |
| `PERFORM DELETE-POLICY` | `deletePolicy(id)` |
| `PERFORM GET-ACTIVE-POLICIES` | `getActivePolicies()` |

## @Autowired Explained

```java
@Autowired
private PolicyRepository policyRepository;
```

This is like CICS LINK:
- In CICS, you `CALL` or `LINK` to a subprogram and CICS finds/loads it for you
- In Spring, `@Autowired` tells Spring to find and inject the `PolicyRepository` automatically
- You don't create it yourself (`new PolicyRepository()`) — Spring manages it

## Key Points

- The Service layer contains all business logic
- It sits between Controller (API) and Repository (database)
- Each method is like a COBOL PERFORM paragraph
- `@Autowired` = Spring's version of CICS LINK (automatic dependency wiring)
- Error handling (orElseThrow) = checking FILE STATUS after VSAM operations
