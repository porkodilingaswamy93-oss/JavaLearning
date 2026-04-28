# Phase 7: Spring Boot Architecture

---

## Mainframe Structure vs Spring Boot Structure

| Mainframe | Spring Boot | Purpose |
|-----------|-------------|---------|
| JCL (Job Control) | `application.properties` | Configuration |
| COBOL Programs | Java Classes (Controller, Service) | Business logic |
| Copybooks | POJO / @Entity Classes | Data structures |
| VSAM Files | Database Tables (via JPA) | Data storage |
| CICS PCT (Program Control Table) | `@RestController` (URL mapping) | Request routing |
| CICS PPT (Processing Program Table) | `@Service` (Business Logic) | Processing |
| SORT/MERGE utilities | Stream API / Spring Batch | Data processing |

---

## Typical Spring Boot Folder Structure

```
src/main/java/com/transamerica/policy/
    PolicyApplication.java             <-- Main class (like JCL JOB card)
    controller/
        PolicyController.java          <-- Handles API requests (like CICS program)
    service/
        PolicyService.java             <-- Business logic (like PERFORM paragraphs)
    repository/
        PolicyRepository.java          <-- Database access (like READ/WRITE VSAM)
    model/
        Policy.java                    <-- Data structure (like Copybook)

src/main/resources/
    application.properties             <-- Config (like JCL PARM, CICS SIT)
```

---

## Layered Architecture

```
    HTTP Request
         |
         v
  +------------------+
  |   Controller     |   Receives request, returns response
  |  (@RestController)|   Like: CICS RECEIVE MAP / SEND MAP
  +------------------+
         |
         v
  +------------------+
  |    Service       |   Business logic
  |   (@Service)     |   Like: COBOL PERFORM paragraphs
  +------------------+
         |
         v
  +------------------+
  |   Repository     |   Database access
  |  (@Repository)   |   Like: VSAM READ/WRITE/DELETE
  +------------------+
         |
         v
  +------------------+
  |   Database       |   PostgreSQL / MySQL
  |                  |   Like: VSAM / DB2
  +------------------+
```

---

## Full Modernization Comparison

| Layer | Mainframe (Before) | Modern (After) |
|-------|-------------------|----------------|
| UI | BMS Maps + CICS SEND/RECEIVE | Angular / React Frontend |
| API | CICS Transaction + COMMAREA | Spring Boot REST Controller |
| Business Logic | COBOL PERFORM paragraphs | Spring @Service methods |
| Data Access | VSAM READ/WRITE, EXEC SQL | Spring Data JPA Repository |
| Database | VSAM KSDS/ESDS, DB2/z | PostgreSQL / MySQL / Oracle |
| Batch | JCL + COBOL batch programs | Spring Batch |
| Scheduling | Control-M / CA7 / TWS | Spring Scheduler / Kubernetes |
| Config | JCL PARM, CICS SIT | application.properties / YAML |
| Deployment | Endevor -> Production | CI/CD -> Docker -> Cloud |

---

## Annotations Cheat Sheet

| Annotation | Purpose | COBOL/Mainframe Equivalent |
|-----------|---------|---------------------------|
| `@SpringBootApplication` | Starts the application | JOB card in JCL |
| `@RestController` | Handles HTTP requests | CICS PCT entry |
| `@GetMapping` | Maps GET request to method | CICS RECEIVE (inquiry) |
| `@PostMapping` | Maps POST request to method | CICS RECEIVE (create) |
| `@PutMapping` | Maps PUT request to method | CICS RECEIVE (update) |
| `@DeleteMapping` | Maps DELETE to method | CICS DELETE |
| `@RequestBody` | JSON input -> Java object | RECEIVE MAP into COMMAREA |
| `@PathVariable` | URL parameter | Transaction input key |
| `@Service` | Business logic class | COBOL program with logic |
| `@Repository` | Database access class | VSAM file handler |
| `@Entity` | Maps class to DB table | Copybook -> Table layout |
| `@Autowired` | Inject dependency | CICS LINK (auto-connect) |
| `@Column` | Maps field to DB column | Copybook field -> DB column |
