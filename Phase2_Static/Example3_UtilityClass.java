// =============================================================================
// Phase 2, Example 3: Static Utility Class and Static Block
// =============================================================================
// A utility class with only static methods — no need to create objects.
// Like a COPY member with reusable paragraphs callable by any program.
//
// Compile: javac Example3_UtilityClass.java
// Run:     java Example3_UtilityClass
// =============================================================================

public class Example3_UtilityClass {

    // -----------------------------------------------------------------
    // STATIC BLOCK — One-time initialization (runs when class loads)
    // -----------------------------------------------------------------
    // Like the initialization paragraph at the start of a batch job.
    static String systemEnvironment;

    static {
        systemEnvironment = "PRODUCTION";
        System.out.println(">>> Static block executed — System initialized as: "
                           + systemEnvironment);
    }

    // -----------------------------------------------------------------
    // STATIC METHODS — Utility functions, no object needed
    // -----------------------------------------------------------------

    static double calculateTax(double amount) {
        return amount * 0.18;
    }

    static String formatCurrency(double amount) {
        return String.format("$%,.2f", amount);
    }

    static boolean isValidPolicyId(String policyId) {
        // Policy ID must start with "POL" and be 6 characters
        return policyId != null
            && policyId.length() == 6
            && policyId.startsWith("POL");
    }

    static String maskPolicyId(String policyId) {
        // Show only last 3 characters: POL001 -> ***001
        if (policyId == null || policyId.length() < 3) return "***";
        return "***" + policyId.substring(policyId.length() - 3);
    }

    // -----------------------------------------------------------------
    // MAIN METHOD
    // -----------------------------------------------------------------
    public static void main(String[] args) {

        // Note: Static block already ran above before main() starts

        System.out.println("\n=== UTILITY METHOD DEMOS ===\n");

        // calculateTax — called on the CLASS, no object needed
        double premium = 5000.00;
        double tax = Example3_UtilityClass.calculateTax(premium);
        System.out.println("Premium: " + formatCurrency(premium));
        System.out.println("Tax:     " + formatCurrency(tax));
        System.out.println("Total:   " + formatCurrency(premium + tax));

        // formatCurrency
        System.out.println("\n=== FORMAT CURRENCY ===\n");
        double[] amounts = {1500.00, 75000.50, 1234567.89};
        for (double amt : amounts) {
            System.out.println(formatCurrency(amt));
        }

        // isValidPolicyId
        System.out.println("\n=== POLICY ID VALIDATION ===\n");
        String[] testIds = {"POL001", "POL123", "INV001", "PO", null};
        for (String id : testIds) {
            System.out.println("  " + id + " -> "
                + (isValidPolicyId(id) ? "VALID" : "INVALID"));
        }

        // maskPolicyId
        System.out.println("\n=== POLICY ID MASKING ===\n");
        String[] policyIds = {"POL001", "POL999", "POL123"};
        for (String id : policyIds) {
            System.out.println("  " + id + " -> " + maskPolicyId(id));
        }

        System.out.println("\nEnvironment: " + systemEnvironment);
    }
}

// Expected Output:
// =============================================================================
// >>> Static block executed — System initialized as: PRODUCTION
//
// === UTILITY METHOD DEMOS ===
//
// Premium: $5,000.00
// Tax:     $900.00
// Total:   $5,900.00
//
// === FORMAT CURRENCY ===
//
// $1,500.00
// $75,000.50
// $1,234,567.89
//
// === POLICY ID VALIDATION ===
//
//   POL001 -> VALID
//   POL123 -> VALID
//   INV001 -> INVALID
//   PO -> INVALID
//   null -> INVALID
//
// === POLICY ID MASKING ===
//
//   POL001 -> ***001
//   POL999 -> ***999
//   POL123 -> ***123
//
// Environment: PRODUCTION
// =============================================================================
