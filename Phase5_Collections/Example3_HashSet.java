// =============================================================================
// Phase 5, Example 3: HashSet and LinkedHashMap
// =============================================================================
// HashSet = unique values only (like SORT with EQUALS DELETE)
// LinkedHashMap = key-value with insertion order (like sequential file)
//
// Compile: javac Example3_HashSet.java
// Run:     java Example3_HashSet
// =============================================================================

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class Example3_HashSet {

    public static void main(String[] args) {

        // ===== HASHSET: Unique values only =====
        System.out.println("=== HASHSET (Remove Duplicates) ===\n");

        HashSet<String> uniqueAgents = new HashSet<>();
        uniqueAgents.add("AGENT001");
        uniqueAgents.add("AGENT002");
        uniqueAgents.add("AGENT001");  // Duplicate — will NOT be added
        uniqueAgents.add("AGENT003");
        uniqueAgents.add("AGENT002");  // Duplicate — will NOT be added

        System.out.println("Unique agents: " + uniqueAgents);
        System.out.println("Count: " + uniqueAgents.size()); // 3, not 5

        // Check membership
        System.out.println("Contains AGENT001? " + uniqueAgents.contains("AGENT001"));
        System.out.println("Contains AGENT999? " + uniqueAgents.contains("AGENT999"));

        // Practical use: find unique policy statuses from a batch
        System.out.println("\n=== FINDING UNIQUE STATUSES ===\n");

        String[] records = {"ACTIVE", "LAPSED", "ACTIVE", "CANCELLED", "ACTIVE", "LAPSED"};
        HashSet<String> uniqueStatuses = new HashSet<>();
        for (String status : records) {
            uniqueStatuses.add(status);
        }
        System.out.println("Unique statuses found: " + uniqueStatuses);


        // ===== LINKEDHASHMAP: Maintains insertion order =====
        System.out.println("\n=== LINKEDHASHMAP (Ordered like Sequential File) ===\n");

        LinkedHashMap<String, String> transactionLog = new LinkedHashMap<>();
        transactionLog.put("TXN001", "PREMIUM PAYMENT");
        transactionLog.put("TXN002", "LOAN DISBURSEMENT");
        transactionLog.put("TXN003", "REINSTATEMENT");

        // Maintains the order you inserted (unlike regular HashMap)
        for (Map.Entry<String, String> entry : transactionLog.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

// Expected Output:
// =============================================================================
// === HASHSET (Remove Duplicates) ===
//
// Unique agents: [AGENT003, AGENT002, AGENT001]
// Count: 3
// Contains AGENT001? true
// Contains AGENT999? false
//
// === FINDING UNIQUE STATUSES ===
//
// Unique statuses found: [CANCELLED, ACTIVE, LAPSED]
//
// === LINKEDHASHMAP (Ordered like Sequential File) ===
//
// TXN001 -> PREMIUM PAYMENT
// TXN002 -> LOAN DISBURSEMENT
// TXN003 -> REINSTATEMENT
// =============================================================================
