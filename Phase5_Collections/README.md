# Phase 5: Java Collections

## Learning Objectives

- Use ArrayList, HashMap, HashSet for dynamic data management
- Sort collections using Collections.sort() with Comparator
- Map each collection type to its mainframe equivalent
- Build a real-world payment batch processor using collections

## Prerequisites

- Phase 1-4 (Loops, Static, POJO, OOP)

## Mainframe Analogy

| Collection | COBOL/Mainframe Equivalent | Key Feature |
|-----------|---------------------------|-------------|
| `ArrayList` | OCCURS table (but dynamic size) | Ordered, allows duplicates |
| `LinkedList` | Sequential file chain | Fast insert/delete |
| `HashMap` | VSAM KSDS | Key-value, fast lookup |
| `LinkedHashMap` | VSAM KSDS + insertion order | Key-value, maintains order |
| `TreeMap` | VSAM KSDS sorted by key | Key-value, sorted |
| `HashSet` | SORT with EQUALS DELETE | Unique values only |
| `TreeSet` | Sorted unique values | Unique + sorted |

## Common Operations Mapping

| Operation | COBOL | Java |
|-----------|-------|------|
| Add item | `MOVE val TO WS-FIELD(idx)`, `ADD 1 TO count` | `list.add(item)` |
| Access by index | `WS-FIELD(INDEX)` | `list.get(index)` |
| Store key-value | `WRITE to VSAM` | `map.put(key, value)` |
| Lookup by key | `READ VSAM KEY IS` | `map.get(key)` |
| Delete | `DELETE from VSAM` | `list.remove(item)` / `map.remove(key)` |
| Check exists | `SEARCH` | `list.contains(item)` / `map.containsKey(key)` |
| Count | `WS-RECORD-COUNT` | `list.size()` / `map.size()` |
| Sort | `SORT utility` | `Collections.sort(list)` |

**Important:** Java arrays/lists start at index 0. COBOL OCCURS tables start at index 1.

## Example Files

| File | Concepts |
|------|----------|
| [Example1_ArrayList.java](Example1_ArrayList.java) | Dynamic list, add/get/remove/contains |
| [Example2_HashMap.java](Example2_HashMap.java) | Key-value access like VSAM KSDS |
| [Example3_HashSet.java](Example3_HashSet.java) | Unique values, LinkedHashMap |
| [Example4_Sorting.java](Example4_Sorting.java) | Sort strings and objects by field |
| [Example5_PaymentBatch.java](Example5_PaymentBatch.java) | Real-world grouping and summarizing |

## COBOL Reference

See [CobolEquivalent.md](CobolEquivalent.md) for VSAM/OCCURS/SORT examples alongside Java.
