# Phase 7: Controller (CICS Transactions to REST Endpoints)

---

In CICS, users type a transaction code (e.g., INQP) and it runs a program. In Spring Boot, clients call a URL (e.g., GET /api/policies) and it runs a method.

## Controller Class

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController                      // This class handles HTTP requests
@RequestMapping("/api/policies")     // Base URL (like CICS transaction group)
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    // GET /api/policies -> Returns all policies (like LSTP transaction)
    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    // GET /api/policies/POL001 -> Returns one policy (like INQP transaction)
    @GetMapping("/{id}")
    public Policy getPolicy(@PathVariable String id) {
        return policyService.getPolicyById(id);
    }

    // POST /api/policies -> Creates a policy (like ADDP transaction)
    // The JSON request body maps to Policy object (like RECEIVE MAP -> COMMAREA)
    @PostMapping
    public Policy createPolicy(@RequestBody Policy policy) {
        return policyService.createPolicy(policy);
    }

    // PUT /api/policies/POL001 -> Updates a policy (like UPDP transaction)
    @PutMapping("/{id}")
    public Policy updatePolicy(@PathVariable String id, @RequestBody Policy policy) {
        return policyService.updatePolicy(id, policy);
    }

    // DELETE /api/policies/POL001 -> Deletes a policy (like DELP transaction)
    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable String id) {
        policyService.deletePolicy(id);
    }
}
```

## CICS Transaction to REST API Mapping

| CICS | Spring Boot | What Happens |
|------|-------------|-------------|
| User types `INQP` + `POL001` | Client sends `GET /api/policies/POL001` | Inquiry |
| User fills map, presses `ADDP` | Client sends `POST /api/policies` with JSON body | Create |
| User modifies map, presses `UPDP` | Client sends `PUT /api/policies/POL001` with JSON body | Update |
| User presses `DELP` | Client sends `DELETE /api/policies/POL001` | Delete |
| User presses `LSTP` | Client sends `GET /api/policies` | List all |

## JSON Request Example (POST)

```json
POST /api/policies
Content-Type: application/json

{
    "policyId": "POL005",
    "insuredName": "NEW CUSTOMER",
    "sumAssured": 75000.00,
    "status": "ACTIVE"
}
```

## JSON Response Example (GET)

```json
GET /api/policies/POL001

{
    "policyId": "POL001",
    "insuredName": "JOHN SMITH",
    "sumAssured": 50000.00,
    "status": "ACTIVE"
}
```

## application.properties Configuration

```properties
# Database connection (like DSN in JCL DD statement)
spring.datasource.url=jdbc:postgresql://localhost:5432/policydb
spring.datasource.username=appuser
spring.datasource.password=secret

# Server port (like CICS listener port)
server.port=8080

# Show SQL queries in log (like DISPLAY in COBOL)
spring.jpa.show-sql=true

# Auto-create tables from Entity classes
spring.jpa.hibernate.ddl-auto=update
```

## Key Annotations

| Annotation | Purpose |
|-----------|---------|
| `@RestController` | Marks class as HTTP request handler |
| `@RequestMapping("/api/policies")` | Base URL for all endpoints in this controller |
| `@GetMapping` | Handles HTTP GET requests |
| `@PostMapping` | Handles HTTP POST requests |
| `@PutMapping` | Handles HTTP PUT requests |
| `@DeleteMapping` | Handles HTTP DELETE requests |
| `@PathVariable` | Extracts value from URL path (e.g., `/policies/POL001` -> `id = "POL001"`) |
| `@RequestBody` | Maps JSON request body to Java object |
