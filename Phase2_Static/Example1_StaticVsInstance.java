// =============================================================================
// Phase 2, Example 1: Static vs Instance Variables and Methods
// =============================================================================
// Static = belongs to CLASS (like WORKING-STORAGE, shared by all)
// Instance = belongs to OBJECT (like LOCAL-STORAGE, each gets its own)
//
// Compile: javac Example1_StaticVsInstance.java
// Run:     java Example1_StaticVsInstance
// =============================================================================

public class Example1_StaticVsInstance {

    // STATIC variable — one copy shared by ALL objects
    // Like WORKING-STORAGE in a CICS program
    static int totalPoliciesProcessed = 0;

    // INSTANCE variables — each object gets its own copy
    // Like LOCAL-STORAGE per CICS task
    String policyId;
    String policyStatus;

    // Constructor — runs when you create a new object
    public Example1_StaticVsInstance(String id, String status) {
        this.policyId = id;
        this.policyStatus = status;
        totalPoliciesProcessed++; // Shared counter increments for every object
    }

    // Instance method — needs an object to call (uses instance data)
    void displayPolicy() {
        System.out.println("Policy: " + policyId + " | Status: " + policyStatus);
    }

    // Static method — no object needed (utility function)
    static double calculateTax(double premiumAmount) {
        double taxRate = 0.18;
        return premiumAmount * taxRate;
    }

    public static void main(String[] args) {

        System.out.println("=== STATIC vs INSTANCE DEMO ===\n");

        // Accessing static variable WITHOUT creating an object
        System.out.println("Policies processed so far: "
                           + Example1_StaticVsInstance.totalPoliciesProcessed);

        // Creating objects (each gets its own policyId and policyStatus)
        Example1_StaticVsInstance pol1 = new Example1_StaticVsInstance("POL001", "ACTIVE");
        Example1_StaticVsInstance pol2 = new Example1_StaticVsInstance("POL002", "ACTIVE");
        Example1_StaticVsInstance pol3 = new Example1_StaticVsInstance("POL003", "LAPSED");

        // Each object has its OWN instance data
        pol1.displayPolicy();
        pol2.displayPolicy();
        pol3.displayPolicy();

        // But ALL share the SAME static counter
        System.out.println("\nTotal policies processed: "
                           + Example1_StaticVsInstance.totalPoliciesProcessed);

        System.out.println("\n=== STATIC METHOD DEMO ===\n");

        // Calling static method — no object needed
        double premium = 5000.00;
        double tax = Example1_StaticVsInstance.calculateTax(premium);
        System.out.println("Premium: $" + premium);
        System.out.println("Tax (18%): $" + tax);
        System.out.println("Total: $" + (premium + tax));

        System.out.println("\n=== POLICY COUNTER UTILITY ===\n");

        PolicyCounter.increment("POL001 - New Business");
        PolicyCounter.increment("POL002 - Premium Payment");
        PolicyCounter.increment("POL003 - Loan Request");
        PolicyCounter.printSummary();
    }
}

// Supporting static utility class
class PolicyCounter {
    private static int count = 0;

    static void increment(String description) {
        count++;
        System.out.println("[" + count + "] Processed: " + description);
    }

    static void printSummary() {
        System.out.println("\n=== Processing Summary ===");
        System.out.println("Total Records: " + count);
    }
}

// Expected Output:
// =============================================================================
// === STATIC vs INSTANCE DEMO ===
//
// Policies processed so far: 0
// Policy: POL001 | Status: ACTIVE
// Policy: POL002 | Status: ACTIVE
// Policy: POL003 | Status: LAPSED
//
// Total policies processed: 3
//
// === STATIC METHOD DEMO ===
//
// Premium: $5000.0
// Tax (18%): $900.0
// Total: $5900.0
//
// === POLICY COUNTER UTILITY ===
//
// [1] Processed: POL001 - New Business
// [2] Processed: POL002 - Premium Payment
// [3] Processed: POL003 - Loan Request
//
// === Processing Summary ===
// Total Records: 3
// =============================================================================
