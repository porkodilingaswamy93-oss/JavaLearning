# Phase 7: Spring Boot Project Setup Guide

Step-by-step guide to create a real Spring Boot project.

---

## Step 1: Go to Spring Initializr

Open [https://start.spring.io](https://start.spring.io) in your browser.

## Step 2: Configure the Project

| Setting | Value |
|---------|-------|
| Project | Maven |
| Language | Java |
| Spring Boot | 3.x (latest stable) |
| Group | `com.transamerica` |
| Artifact | `policy-service` |
| Name | `policy-service` |
| Package name | `com.transamerica.policyservice` |
| Packaging | Jar |
| Java | 17 |

## Step 3: Add Dependencies

Click "ADD DEPENDENCIES" and select:
- **Spring Web** — for REST APIs
- **Spring Data JPA** — for database access
- **PostgreSQL Driver** — for PostgreSQL database
- (Optional) **Spring Boot DevTools** — for auto-restart during development

## Step 4: Download and Extract

Click "GENERATE" to download a `.zip` file. Extract it to your workspace.

## Step 5: Open in IDE

Open the extracted folder in IntelliJ IDEA or Eclipse:
- IntelliJ: File -> Open -> select the folder
- Eclipse: File -> Import -> Maven -> Existing Maven Project

## Step 6: Create the Classes

Create these files in `src/main/java/com/transamerica/policyservice/`:

1. **model/Policy.java** — Entity class (see [EntityExample.md](EntityExample.md))
2. **repository/PolicyRepository.java** — Repository interface (see [RepositoryExample.md](RepositoryExample.md))
3. **service/PolicyService.java** — Service class (see [ServiceExample.md](ServiceExample.md))
4. **controller/PolicyController.java** — Controller class (see [ControllerExample.md](ControllerExample.md))

## Step 7: Configure application.properties

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/policydb
spring.datasource.username=appuser
spring.datasource.password=secret
server.port=8080
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

For quick testing without PostgreSQL, use H2 in-memory database instead:
```properties
spring.datasource.url=jdbc:h2:mem:policydb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```
(Add H2 Database dependency in pom.xml for this.)

## Step 8: Run the Application

From the project root directory:
```bash
./mvnw spring-boot:run
```

Or in your IDE, run `PolicyServiceApplication.java` (the class with `@SpringBootApplication`).

## Step 9: Test the APIs

Using curl or Postman:

```bash
# List all policies
curl http://localhost:8080/api/policies

# Get one policy
curl http://localhost:8080/api/policies/POL001

# Create a policy
curl -X POST http://localhost:8080/api/policies \
  -H "Content-Type: application/json" \
  -d '{"policyId":"POL001","insuredName":"JOHN SMITH","sumAssured":50000,"status":"ACTIVE"}'

# Delete a policy
curl -X DELETE http://localhost:8080/api/policies/POL001
```

---

## Key Takeaway for Your Modernization Role

| What You Know (Mainframe) | What You Build (Spring Boot) |
|--------------------------|----------------------------|
| COBOL Copybook | `@Entity` class (Policy.java) |
| VSAM READ/WRITE | `@Repository` (PolicyRepository.java) |
| PERFORM paragraphs | `@Service` methods (PolicyService.java) |
| CICS Transaction | `@RestController` endpoints (PolicyController.java) |
| JCL PARM / config | `application.properties` |
