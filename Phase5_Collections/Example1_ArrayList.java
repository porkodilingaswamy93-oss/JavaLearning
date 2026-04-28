// =============================================================================
// Phase 5, Example 1: ArrayList (Dynamic OCCURS Table)
// =============================================================================
// ArrayList grows automatically — no fixed size like COBOL OCCURS.
//
// Compile: javac Example1_ArrayList.java
// Run:     java Example1_ArrayList
// =============================================================================

import java.util.ArrayList;

public class Example1_ArrayList {

    public static void main(String[] args) {

        System.out.println("=== ARRAYLIST BASICS ===\n");

        // Create (like defining an OCCURS table, but no max size)
        ArrayList<String> policyList = new ArrayList<>();

        // Add items (like MOVE value TO WS-POL-ID(index), ADD 1 TO count)
        policyList.add("POL001");
        policyList.add("POL002");
        policyList.add("POL003");
        policyList.add("POL004");

        // Size (like WS-POLICY-COUNT)
        System.out.println("Total policies: " + policyList.size());

        // Access by index (IMPORTANT: Java starts at 0, COBOL at 1)
        System.out.println("First policy: " + policyList.get(0));
        System.out.println("Second policy: " + policyList.get(1));

        // Iterate (like PERFORM VARYING)
        System.out.println("\nAll policies:");
        for (String polId : policyList) {
            System.out.println("  " + polId);
        }

        // Remove (no direct COBOL equivalent)
        policyList.remove("POL002");
        System.out.println("\nAfter removing POL002: " + policyList);

        // Check if exists (like SEARCH)
        System.out.println("Contains POL003? " + policyList.contains("POL003"));
        System.out.println("Contains POL002? " + policyList.contains("POL002"));


        System.out.println("\n=== ARRAYLIST WITH OBJECTS ===\n");

        ArrayList<PolData> policies = new ArrayList<>();
        policies.add(new PolData("POL001", "JOHN SMITH", 50000.00, "ACTIVE"));
        policies.add(new PolData("POL002", "JANE DOE", 75000.00, "ACTIVE"));
        policies.add(new PolData("POL003", "BOB WILSON", 30000.00, "LAPSED"));
        policies.add(new PolData("POL004", "ANN TAYLOR", 100000.00, "ACTIVE"));

        // Process like a batch — find all active policies
        double totalSumAssured = 0;
        for (PolData pol : policies) {
            if (pol.getStatus().equals("ACTIVE")) {
                totalSumAssured += pol.getSumAssured();
                System.out.println("Active: " + pol.getPolicyId()
                    + " | " + pol.getName() + " | $" + pol.getSumAssured());
            }
        }
        System.out.println("Total Active Sum Assured: $" + totalSumAssured);
    }
}

class PolData {
    private String policyId;
    private String name;
    private double sumAssured;
    private String status;

    public PolData(String policyId, String name, double sumAssured, String status) {
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
// === ARRAYLIST BASICS ===
//
// Total policies: 4
// First policy: POL001
// Second policy: POL002
//
// All policies:
//   POL001
//   POL002
//   POL003
//   POL004
//
// After removing POL002: [POL001, POL003, POL004]
// Contains POL003? true
// Contains POL002? false
//
// === ARRAYLIST WITH OBJECTS ===
//
// Active: POL001 | JOHN SMITH | $50000.0
// Active: POL002 | JANE DOE | $75000.0
// Active: POL004 | ANN TAYLOR | $100000.0
// Total Active Sum Assured: $225000.0
// =============================================================================
