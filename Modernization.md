# Legacy-to-target migration mapping

## Project slice

Policy issuance is modeled as a vertical slice: capture a request, validate its customer/product/effective date/premium, allocate a policy number, persist the result and return a confirmation. The code is a learning example, not an automated COBOL converter.

## Component transformation

| Legacy component | Target component | Transformation rule |
|---|---|---|
| CICS/BMS map | Angular form + REST API | Rebuild navigation and validation; enforce all business validation on the server. |
| COBOL online program | Spring Boot controller + service | Controller owns HTTP binding; service owns business rules and transaction boundary. |
| COBOL copybook | Java DTO/entity + explicit mapper | Map PIC types, lengths, signedness, implied decimals, dates, OCCURS and REDEFINES deliberately. |
| JCL JOB / EXEC / PROC | Scheduler + Spring Batch Job/Step | Recreate step dependencies, conditions, restart rules, return codes, notifications and ownership. |
| JCL DD / DCB / DISP | Resource configuration and reader/writer | Preserve the record contract, encoding, LRECL/RECFM, retention and failure cleanup. |
| VSAM KSDS | PostgreSQL table + primary/unique keys and indexes | Preserve business keys and write semantics; retain legacy IDs if externally visible. |
| VSAM ESDS / RRDS | Relational table plus explicit sequence/slot only when required | Do not infer business order from a physical location. |
| DB2 tables | PostgreSQL tables | Map types, nullability, constraints, generated keys, indexes and referential integrity. |
| DB2 embedded SQL / cursors | Spring Data JPA or JDBC | Translate SQL and transaction boundaries; tune against PostgreSQL execution plans. |
| DB2 SQLCODE handling | Exception / SQLSTATE handling | Distinguish business rejects from duplicate, timeout and transient concurrency errors. |
| EBCDIC fixed records / COMP-3 | Validated ingestion/normalization stage | Decode charset and binary numeric formats before parsing application-level values. |

## Fixed-width copybook example

The sample `POLREQ.cpy` contains these fields:

| Field | Width | Java form | Rule |
|---|---:|---|---|
| `CUSTOMER-ID PIC X(10)` | 10 bytes/positions | `String` | Decide how spaces are interpreted; validate length. |
| `PRODUCT-CODE PIC X(4)` | 4 | `String` | Do not silently change case or trim if that changes the source contract. |
| `EFFECTIVE-DATE PIC X(10)` | 10 | `LocalDate` after parsing | Reject unsupported formats and impossible dates. |
| `ANNUAL-PREMIUM PIC 9(7)V99` | 9 display digits | `BigDecimal` with scale 2 | The implied decimal point is not stored; do not use `float`/`double` for money. |

Total record length is 33 positions for this simplified definition. This example does not decode EBCDIC or COMP-3. Production ingestion must use the actual CCSID, record format, field offsets, sign nibbles and sample bytes.

## JCL replacement

| JCL concept | Modern equivalent | Test/operational control |
|---|---|---|
| `JOB` | Job identity + scheduler context | Run identity, ownership, schedule and alert route |
| `EXEC PGM=...` | Spring Batch step / tasklet | Step inputs, exits, run order and restart point |
| `STEPLIB` | Versioned jar/container image | Reproducible artifact and controlled release |
| `DD` dataset | Configured file, object-store resource or database input | Encoding/layout, permissions, retention, cleanup |
| `DISP`, `SPACE`, `DCB` | Resource lifecycle, storage policy, input schema | Dataset creation/deletion semantics, record length and format |
| `COND`, `IF/THEN/ELSE` | Explicit job-flow transitions | Return-code equivalence and failure routing |
| Restart procedure | JobRepository + execution context + idempotent writer | Restart from failed step/chunk without duplicate business effects |
| `SYSOUT`, `SYSUDUMP` | Structured logs, metrics, traces and secured diagnostics | Correlation IDs, error classification and PII-safe logging |

The sample Spring Batch reader expects an already-normalized CSV. Do not point it directly at arbitrary mainframe bytes. A production ingestion stage should decode and validate the source record first, and route invalid records into an auditable reject stream.

## DB2 to PostgreSQL differences to check

- `CHAR` trailing-space comparison versus PostgreSQL behavior; blank versus `NULL` conventions.
- `DECIMAL(p,s)` precision and rounding; use `BigDecimal` in Java.
- Date and timestamp parsing, timezone assumptions and default values.
- Identity/sequence behavior and preservation of externally visible legacy identifiers.
- Constraints and duplicate detection: DB2 SQLCODE `-803` often corresponds to PostgreSQL unique violation SQLSTATE `23505`; mapping is semantic, not numeric.
- Lock/deadlock/timeout handling, transaction isolation, cursor ordering, stored procedures and explain plans.

## Migration problems and optimizations

| Problem | Resolution / optimization | Proof required |
|---|---|---|
| Business rules spread across CICS/COBOL online and batch paths | Build a rule catalogue, call/dependency map and decision table; centralize reusable rules in a service | Golden-master comparisons and business owner approval |
| Numeric layout changes silently corrupt money | Explicit byte/field mapping, `BigDecimal`, precision/scale checks and boundary tests | Compare source/target amount totals and signed edge cases |
| JCL restart/rerun creates duplicate business effects | Define idempotency key, unique DB constraint, job parameters, checkpoint/retry policy and reject handling | Simulated failure/restart, duplicate-request and concurrency tests |
| VSAM behavior is mistaken for relational behavior | Model stable keys, update semantics and only genuine ordering/slot dependencies | Key collision and ordered-read scenarios |
| DB2 queries regress under PostgreSQL | Port SQL deliberately, add workload-driven indexes and inspect `EXPLAIN ANALYZE` plans | Production-like response time and batch-window test |
| Cutover exposes data drift or hidden dependents | Run parallel/shadow comparisons; reconcile keys, counts, totals, rejects and downstream effects | Signed reconciliation report, rollback rehearsal and monitored observation period |

## Outcome evidence

The project brief states 30% lower operational cost after decommissioning migrated mainframe components and zero business disruption at go-live. Treat these as reported outcomes, not as independently verified measurements. For a real interview/resume, substantiate them with comparable before/after cost baselines, production incident/outage records, transaction monitoring and reconciliation sign-off. Do not invent latency or batch-duration improvements without test/production measurements.
