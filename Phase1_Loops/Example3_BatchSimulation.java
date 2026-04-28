// =============================================================================
// Phase 1, Example 3: Batch Processing Simulation
// =============================================================================
// This mimics a mainframe batch job that reads policy records, validates them,
// and counts good/bad records — just like COBOL batch programs in Farmers Life
// or Transamerica.
//
// Compile: javac Example3_BatchSimulation.java
// Run:     java Example3_BatchSimulation
// =============================================================================

public class Example3_BatchSimulation {

    public static void main(String[] args) {

        System.out.println("=== BATCH PROCESSING SIMULATION ===\n");

        // Simulated input file — each string is a record with comma-separated fields
        // Format: PolicyId,Status,PremiumAmount
        String[] policyRecords = {
            "POL001,ACTIVE,1500.00",
            "POL002,LAPSED,0.00",
            "POL003,ACTIVE,2300.50",
            "POL004,CANCELLED,0.00",
            "POL005,ACTIVE,800.00"
        };

        int goodCount = 0;
        int badCount = 0;
        double totalPremium = 0.0;

        // Process each record — like PERFORM UNTIL END-OF-FILE
        for (String record : policyRecords) {
            // Split the record into fields (like moving from record area to fields)
            String[] fields = record.split(",");
            String polId = fields[0];
            String polStatus = fields[1];
            double premium = Double.parseDouble(fields[2]);

            // Validate — skip non-ACTIVE records
            if (!polStatus.equals("ACTIVE")) {
                badCount++;
                System.out.println("SKIPPED: " + polId + " (Status: " + polStatus + ")");
                continue;
            }

            // Process good records
            goodCount++;
            totalPremium += premium;
            System.out.println("PROCESSED: " + polId + " Premium: $" + premium);
        }

        // Print batch summary (like end-of-job report)
        System.out.println("\n--- Batch Summary ---");
        System.out.println("Records Processed: " + goodCount);
        System.out.println("Records Skipped:   " + badCount);
        System.out.println("Total Premium:     $" + totalPremium);
    }
}

// Expected Output:
// =============================================================================
// === BATCH PROCESSING SIMULATION ===
//
// PROCESSED: POL001 Premium: $1500.0
// SKIPPED: POL002 (Status: LAPSED)
// PROCESSED: POL003 Premium: $2300.5
// SKIPPED: POL004 (Status: CANCELLED)
// PROCESSED: POL005 Premium: $800.0
//
// --- Batch Summary ---
// Records Processed: 3
// Records Skipped:   2
// Total Premium:     $4600.5
// =============================================================================
