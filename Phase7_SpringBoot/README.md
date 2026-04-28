# Phase 7: Spring Boot Introduction

## Learning Objectives

- Understand Spring Boot architecture and how it maps to CICS/COBOL
- Know the layered architecture: Controller -> Service -> Repository -> Database
- Map CICS transactions to REST API endpoints
- Understand Entity, Repository, Service, and Controller roles

## Prerequisites

- Phase 1-6 (all Java fundamentals)

## Important Note

This phase is conceptual with code in markdown blocks. Spring Boot needs a project setup (Maven/Gradle) to run. See [ProjectSetupGuide.md](ProjectSetupGuide.md) for how to create a real project.

## Big Picture: Mainframe to Spring Boot

| Mainframe (Before) | Spring Boot (After) | Purpose |
|--------------------|--------------------|---------|
| CICS Region | Spring Boot Application | The runtime |
| CICS Transaction (INQP) | REST API Endpoint (GET /policy) | Entry point |
| CICS Program | Controller + Service classes | Request handling |
| COMMAREA | JSON Request/Response | Data exchange |
| Copybook | @Entity POJO class | Data structure |
| VSAM / DB2 | Spring Data JPA + PostgreSQL | Data storage |
| BMS Map + SEND/RECEIVE | Angular/React Frontend | User interface |
| JCL Batch Job | Spring Batch | Batch processing |
| Control-M / CA7 | Spring Scheduler / Kubernetes | Job scheduling |
| JCL PARM / CICS SIT | application.properties | Configuration |
| Endevor | CI/CD + Docker | Deployment |

In your Farmers Life project:
- Angular Frontend replaced CICS screens/BMS maps
- Spring Boot Backend replaced COBOL programs + CICS logic
- PostgreSQL replaced VSAM/DB2
- REST APIs replaced CICS transactions

## Guide Files

| File | Topic |
|------|-------|
| [CobolEquivalent.md](CobolEquivalent.md) | CICS transaction flow vs Spring Boot |
| [Architecture.md](Architecture.md) | Full layer comparison and folder structure |
| [EntityExample.md](EntityExample.md) | Copybook to @Entity class |
| [RepositoryExample.md](RepositoryExample.md) | VSAM I/O to Spring Data JPA |
| [ServiceExample.md](ServiceExample.md) | PERFORM paragraphs to @Service methods |
| [ControllerExample.md](ControllerExample.md) | CICS transactions to REST endpoints |
| [ProjectSetupGuide.md](ProjectSetupGuide.md) | How to create a Spring Boot project |
