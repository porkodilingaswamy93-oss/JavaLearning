// =============================================================================
// Phase 5, Example 2: HashMap (VSAM KSDS Key-Value Access)
// =============================================================================
// HashMap stores key-value pairs for instant lookup — like VSAM KSDS.
//
// Compile: javac Example2_HashMap.java
// Run:     java Example2_HashMap
// =============================================================================

import java.util.HashMap;
import java.util.Map;

public class Example2_HashMap {

    public static void main(String[] args) {

        System.out.println("=== HASHMAP (VSAM KSDS) ===\n");

        HashMap<String, PolicyInfo> policyMap = new HashMap<>();

        // PUT = Write a record to VSAM with a key
        policyMap.put("POL001", new PolicyInfo("POL001", "JOHN SMITH", 50000.00, "ACTIVE"));
        policyMap.put("POL002", new PolicyInfo("POL002", "JANE DOE", 75000.00, "ACTIVE"));
        policyMap.put("POL003", new PolicyInfo("POL003", "BOB WILSON", 30000.00, "LAPSED"));

        // GET = READ by key (like READ VSAM KEY IS 'POL002')
        PolicyInfo found = policyMap.get("POL002");
        if (found != null) {
            System.out.println("Found: " + found.getPolicyId() + " - " + found.getName());
        }

        // CHECK if key exists (like checking VSAM file status after READ)
        System.out.println("POL001 exists? " + policyMap.containsKey("POL001"));
        System.out.println("POL999 exists? " + policyMap.containsKey("POL999"));

        // ITERATE all entries (like sequential READ of VSAM)
        System.out.println("\nAll policies in map:");
        for (Map.Entry<String, PolicyInfo> entry : policyMap.entrySet()) {
            System.out.println("  Key: " + entry.getKey()
                + " -> " + entry.getValue().getName()
                + " | $" + entry.getValue().getSumAssured());
        }

        // REMOVE a record (like DELETE in VSAM)
        policyMap.remove("POL003");
        System.out.println("\nAfter removing POL003, size: " + policyMap.size());

        // getOrDefault — safe access with fallback
        PolicyInfo missing = policyMap.getOrDefault("POL999", null);
        System.out.println("POL999 lookup: " + (missing != null ? missing.getName() : "NOT FOUND"));
    }
}

class PolicyInfo {
    private String policyId;
    private String name;
    private double sumAssured;
    private String status;

    public PolicyInfo(String policyId, String name, double sumAssured, String status) {
        this.policyId = policyId;
        this.name = name;
        this.sumAssured = sumAssured;
        this.status = status;
    }

    public String getPolicyId()   { return policyId; }
    public String getName()       { return name; }
    public double getSumAssured() { return sumAssured; }
    public String getStatus()     { return status; }
}

// Expected Output:
// =============================================================================
// === HASHMAP (VSAM KSDS) ===
//
// Found: POL002 - JANE DOE
// POL001 exists? true
// POL999 exists? false
//
// All policies in map:
//   Key: POL001 -> JOHN SMITH | $50000.0
//   Key: POL002 -> JANE DOE | $75000.0
//   Key: POL003 -> BOB WILSON | $30000.0
//
// After removing POL003, size: 2
// POL999 lookup: NOT FOUND
// =============================================================================
