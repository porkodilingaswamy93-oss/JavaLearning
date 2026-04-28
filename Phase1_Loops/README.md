# Phase 1: Loops in Java

## Learning Objectives

- Understand all Java loop types: `for`, `while`, `do-while`, enhanced `for-each`
- Map each loop to its COBOL PERFORM equivalent
- Use `break` and `continue` for flow control
- Apply loops in a batch processing simulation

## Prerequisites

- Basic COBOL knowledge (PERFORM VARYING, PERFORM UNTIL)
- Java JDK installed

## Mainframe Analogy

| COBOL | Java | When to Use |
|-------|------|-------------|
| `PERFORM VARYING idx FROM 1 BY 1 UNTIL idx > n` | `for (int i = 1; i <= n; i++)` | Known number of iterations |
| `PERFORM UNTIL condition` | `while (!condition)` | Loop until condition met (test before) |
| `PERFORM WITH TEST AFTER UNTIL condition` | `do { } while (!condition)` | Run at least once (test after) |
| `PERFORM paragraph N TIMES` | `for (int i = 0; i < n; i++)` | Fixed count |
| Nested `PERFORM VARYING` | Nested `for` loops | Multi-level iteration |
| `GO TO` / `EXIT PERFORM` | `break` | Exit loop early |
| `NEXT SENTENCE` / `CONTINUE` | `continue` | Skip to next iteration |

## Key Difference

In COBOL, loop variables like `WS-INDEX` are declared in WORKING-STORAGE and persist after the loop ends. In Java, variables declared inside a `for` loop (like `int i`) are scoped to the loop and do not exist outside it.

## Example Files

| File | Concepts |
|------|----------|
| [Example1_BasicLoops.java](Example1_BasicLoops.java) | for, while, do-while |
| [Example2_EnhancedAndNested.java](Example2_EnhancedAndNested.java) | for-each, nested loops, break, continue |
| [Example3_BatchSimulation.java](Example3_BatchSimulation.java) | Real-world batch processing with loops |

## COBOL Reference

See [CobolEquivalent.md](CobolEquivalent.md) for full COBOL code samples alongside Java.
