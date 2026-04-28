# Interview Questions: Collections

---

## Q1: ArrayList vs LinkedList — when to use which?

**Answer:**

| | ArrayList | LinkedList |
|-|-----------|-----------|
| Internal structure | Dynamic array | Doubly linked list |
| Random access (get by index) | Fast O(1) | Slow O(n) |
| Insert/Delete at middle | Slow O(n) | Fast O(1) |
| Memory | Less overhead | More (stores prev/next pointers) |
| COBOL analogy | VSAM RRDS (direct access) | Chain of pointers |

**In modernization: ArrayList is used 90% of the time.**

---

## Q2: HashMap vs TreeMap?

**Answer:**

| | HashMap | TreeMap |
|-|---------|---------|
| Ordering | No guaranteed order | Sorted by key |
| Lookup speed | O(1) average | O(log n) |
| Null keys | Allows one null key | Does not allow null keys |
| COBOL analogy | VSAM KSDS with direct key access | VSAM KSDS browsed in key order |

Use `HashMap` when you just need fast lookup. Use `TreeMap` when you need sorted keys.

---

## Q3: How does HashMap work internally?

**Answer (simplified):**

HashMap uses an array of "buckets" internally.

**put(key, value):**
1. Compute `hashCode()` of key (generates a number)
2. Use that number to find which bucket (array index)
3. Store the key-value pair in that bucket
4. If two keys hash to the same bucket (collision), store as a linked list

**get(key):**
1. Compute `hashCode()` of key
2. Go directly to the bucket
3. Return the value

This is why lookup is O(1) — nearly instant, like direct VSAM access.

```java
// hashCode() determines the bucket
"POL001".hashCode()  // Returns some integer, say 123456
// 123456 % arraySize = bucket index
```

---

## Q4: How do you remove duplicates from a list?

**Answer:** Use a `HashSet`.

```java
List<String> withDuplicates = Arrays.asList("A", "B", "A", "C", "B");
Set<String> unique = new HashSet<>(withDuplicates);
List<String> noDuplicates = new ArrayList<>(unique);
// Result: [A, B, C]
```

To preserve order, use `LinkedHashSet`:
```java
Set<String> unique = new LinkedHashSet<>(withDuplicates);
// Result: [A, B, C] (in original order)
```

COBOL equivalent: `SORT ... SUM FIELDS=NONE` (removes duplicates during sort).
