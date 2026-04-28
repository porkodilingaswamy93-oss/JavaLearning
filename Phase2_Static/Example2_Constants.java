// =============================================================================
// Phase 2, Example 2: Static Final Constants
// =============================================================================
// COBOL equivalent: 78-level values and VALUE clauses that never change.
// 'static final' = constant. Set once, never changes.
//
// Compile: javac Example2_Constants.java
// Run:     java Example2_Constants
// =============================================================================

public class Example2_Constants {

    // Constants — like 78-level or VALUE clauses in COBOL
    // COBOL: 78 WS-MAX-POLICIES VALUE 1000.
    static final int MAX_POLICIES = 1000;

    // COBOL: 01 WS-COMPANY-NAME PIC X(20) VALUE 'TRANSAMERICA'.
    static final String COMPANY_NAME = "TRANSAMERICA";

    // COBOL: 01 WS-TAX-RATE PIC V99 VALUE 0.18.
    static final double TAX_RATE = 0.18;

    // COBOL: 01 WS-MIN-PREMIUM PIC 9(07)V99 VALUE 500.00.
    static final double MIN_PREMIUM = 500.00;

    public static void main(String[] args) {

        System.out.println("=== STATIC FINAL CONSTANTS DEMO ===\n");

        System.out.println("Company: " + COMPANY_NAME);
        System.out.println("Max Policies Allowed: " + MAX_POLICIES);
        System.out.println("Tax Rate: " + TAX_RATE);
        System.out.println("Minimum Premium: $" + MIN_PREMIUM);

        // Using constants in calculations
        System.out.println("\n=== USING CONSTANTS IN CALCULATIONS ===\n");

        double premium = 5000.00;
        double tax = premium * TAX_RATE;
        double total = premium + tax;

        System.out.println("Premium: $" + premium);
        System.out.println("Tax (" + (TAX_RATE * 100) + "%): $" + tax);
        System.out.println("Total: $" + total);

        // Validate against constants
        System.out.println("\n=== VALIDATION WITH CONSTANTS ===\n");

        double[] testPremiums = {200.00, 500.00, 1500.00};
        for (double p : testPremiums) {
            if (p < MIN_PREMIUM) {
                System.out.println("$" + p + " -> REJECTED (below minimum $" + MIN_PREMIUM + ")");
            } else {
                System.out.println("$" + p + " -> ACCEPTED");
            }
        }

        // Uncommenting the next line would cause a COMPILE ERROR:
        // COMPANY_NAME = "OTHER";  // ERROR: cannot assign to final variable
    }
}

// Expected Output:
// =============================================================================
// === STATIC FINAL CONSTANTS DEMO ===
//
// Company: TRANSAMERICA
// Max Policies Allowed: 1000
// Tax Rate: 0.18
// Minimum Premium: $500.0
//
// === USING CONSTANTS IN CALCULATIONS ===
//
// Premium: $5000.0
// Tax (18.0%): $900.0
// Total: $5900.0
//
// === VALIDATION WITH CONSTANTS ===
//
// $200.0 -> REJECTED (below minimum $500.0)
// $500.0 -> ACCEPTED
// $1500.0 -> ACCEPTED
// =============================================================================
