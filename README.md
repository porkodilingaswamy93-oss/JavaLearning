# Java Roadmap for Mainframe Developers

A structured learning path for COBOL/mainframe developers transitioning to Java and Spring Boot, with side-by-side mainframe equivalents for every concept.

---

## How to Use This Curriculum

1. Follow the phases in order — each builds on the previous one.
2. Read the `README.md` in each phase folder for theory and mainframe analogies.
3. Read `CobolEquivalent.md` for side-by-side COBOL vs Java code.
4. Compile and run each `.java` example file independently.
5. Refer to [DATATYPE_MAPPING.md](DATATYPE_MAPPING.md) whenever you need to convert a PIC clause.

### Compiling and Running

```bash
cd Phase1_Loops
javac Example1_BasicLoops.java
java Example1_BasicLoops
```

Each `.java` file is self-contained and independently compilable.

---

## 8-Phase Roadmap

| Phase | Topic | Mainframe Equivalent | Est. Time |
|-------|-------|---------------------|-----------|
| [Phase 1](Phase1_Loops/) | Loops | PERFORM VARYING / UNTIL | 2-3 days |
| [Phase 2](Phase2_Static/) | Static Keyword | WORKING-STORAGE / Constants | 1-2 days |
| [Phase 3](Phase3_POJO/) | POJO Classes | Copybooks (01-level structures) | 2-3 days |
| [Phase 4](Phase4_Inheritance/) | Inheritance and OOP | COPY/REPLACING, Subprograms | 3-4 days |
| [Phase 5](Phase5_Collections/) | Collections | VSAM, OCCURS tables, SORT | 3-4 days |
| [Phase 6](Phase6_Java8/) | Java 8 Features | JCL SORT pipelines | 3-4 days |
| [Phase 7](Phase7_SpringBoot/) | Spring Boot | CICS Transactions, REST APIs | 5-7 days |
| [Phase 8](Phase8_Interview/) | Interview Preparation | -- | 3-5 days |

**Total estimated time: 4-6 weeks**

---

## Dependency Graph

```
Phase 1 (Loops) -----> Phase 2 (Static) -----> Phase 3 (POJO)
                                                     |
                                                     v
                        Phase 5 (Collections) <-- Phase 4 (OOP)
                              |
                              v
                        Phase 6 (Java 8)
                              |
                              v
                        Phase 7 (Spring Boot)
                              |
                              v
                        Phase 8 (Interview)
```

---

## Prerequisites

- COBOL programming experience (PERFORM, copybooks, VSAM, CICS)
- Basic understanding of mainframe batch processing (JCL, SORT)
- Java JDK 17+ installed ([download](https://adoptium.net))
- A text editor or IDE (VS Code, IntelliJ, or Eclipse)

---

## Reference

- [DATATYPE_MAPPING.md](DATATYPE_MAPPING.md) — Complete COBOL PIC to Java type reference
- Each phase folder contains a `CobolEquivalent.md` with full COBOL code samples

---

## Context

This curriculum is designed for mainframe developers (12+ years COBOL/CICS/VSAM/DB2 experience) transitioning to Mainframe Modernization roles. Examples use the insurance/payment domain (Transamerica, Farmers Life context).
