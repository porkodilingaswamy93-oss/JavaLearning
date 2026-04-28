# Interview Questions: Spring Boot

---

## Q1: What is dependency injection?

**Answer:**

Instead of a class creating its own dependencies, Spring "injects" them automatically.

COBOL analogy: Like CICS LINK — you don't load the subprogram yourself; CICS finds it and links it to your program.

```java
@Service
public class PolicyService {

    @Autowired   // Spring finds and injects PolicyRepository automatically
    private PolicyRepository policyRepository;

    // You don't write: new PolicyRepository()
    // Spring creates it and gives it to you
}
```

Benefits:
- Loose coupling (classes don't depend on specific implementations)
- Easy to test (swap real database with mock)
- Spring manages object lifecycle

---

## Q2: Explain the Spring Boot layered architecture.

**Answer:**

```
Controller  ->  Service  ->  Repository  ->  Database
```

| Layer | Annotation | Responsibility | COBOL Equivalent |
|-------|-----------|---------------|-----------------|
| Controller | `@RestController` | Receives HTTP request, returns response | CICS RECEIVE MAP / SEND MAP |
| Service | `@Service` | Business logic | COBOL PERFORM paragraphs |
| Repository | `@Repository` | Database operations | VSAM READ/WRITE/DELETE |
| Entity | `@Entity` | Data structure | Copybook |

Data flows: Request -> Controller -> Service -> Repository -> DB -> back up the chain.

---

## Q3: What is the difference between @Component, @Service, @Repository, and @Controller?

**Answer:**

All four tell Spring "manage this class for me." The difference is intent:

| Annotation | Intent | COBOL Analogy |
|-----------|--------|---------------|
| `@Component` | Generic Spring-managed class | General utility module |
| `@Service` | Business logic class | COBOL program with PERFORM paragraphs |
| `@Repository` | Database access class | I/O module that reads/writes VSAM/DB2 |
| `@Controller` | HTTP request handler | CICS program that handles a transaction |

Technically they're interchangeable, but using the correct one makes code readable and enables Spring-specific features (like automatic exception translation for `@Repository`).

---

## Q4: What annotations are used for REST APIs?

**Answer:**

| Annotation | HTTP Method | CICS Equivalent | Example |
|-----------|-------------|----------------|---------|
| `@GetMapping` | GET | CICS inquiry (INQP) | `@GetMapping("/{id}")` |
| `@PostMapping` | POST | CICS create (ADDP) | `@PostMapping` |
| `@PutMapping` | PUT | CICS update (UPDP) | `@PutMapping("/{id}")` |
| `@DeleteMapping` | DELETE | CICS delete (DELP) | `@DeleteMapping("/{id}")` |
| `@RequestBody` | — | RECEIVE MAP into COMMAREA | `@RequestBody Policy policy` |
| `@PathVariable` | — | Transaction input key | `@PathVariable String id` |

```java
@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @GetMapping("/{id}")
    public Policy getPolicy(@PathVariable String id) {
        return policyService.getPolicyById(id);
    }

    @PostMapping
    public Policy createPolicy(@RequestBody Policy policy) {
        return policyService.createPolicy(policy);
    }
}
```
