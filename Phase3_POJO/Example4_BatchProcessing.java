// =============================================================================
// Phase 3, Example 4: Full Batch Processing with POJOs
// =============================================================================
// Combines PolicyRecord and PaymentRecord concepts into a complete
// batch simulation — just like a COBOL batch program.
//
// Compile: javac Example4_BatchProcessing.java
// Run:     java Example4_BatchProcessing
// =============================================================================

public class Example4_BatchProcessing {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println(" BATCH PROCESSING REPORT                    ");
        System.out.println("============================================\n");

        // Simulated input records
        BatchRecord[] records = {
            new BatchRecord("POL001", "JOHN SMITH", "ACTIVE", 1500.00, "PREMIUM"),
            new BatchRecord("POL002", "JANE DOE", "ACTIVE", 5000.00, "LOAN"),
            new BatchRecord("POL003", "BOB WILSON", "LAPSED", 800.00, "PREMIUM"),
            new BatchRecord("POL004", "ANN TAYLOR", "ACTIVE", 2000.00, "PREMIUM"),
            new BatchRecord("POL005", "TOM BROWN", "CANCELLED", 0.00, "REINSTATEMENT"),
            new BatchRecord("POL006", "SUE DAVIS", "ACTIVE", 3500.00, "PREMIUM")
        };

        int totalRecords = records.length;
        int processedCount = 0;
        int skippedCount = 0;
        double totalPremiums = 0;
        double totalLoans = 0;

        System.out.println("--- Processing Records ---\n");

        for (BatchRecord rec : records) {
            // Validate: only process ACTIVE policies
            if (!rec.getStatus().equals("ACTIVE")) {
                skippedCount++;
                System.out.println("SKIP: " + rec.getPolicyId()
                    + " | " + rec.getName()
                    + " | Status: " + rec.getStatus());
                continue;
            }

            processedCount++;

            // Accumulate by transaction type
            if (rec.getTxnType().equals("PREMIUM")) {
                totalPremiums += rec.getAmount();
            } else if (rec.getTxnType().equals("LOAN")) {
                totalLoans += rec.getAmount();
            }

            System.out.println("PROC: " + rec.getPolicyId()
                + " | " + rec.getName()
                + " | " + rec.getTxnType()
                + " | $" + rec.getAmount());
        }

        // Print batch summary report
        System.out.println("\n============================================");
        System.out.println(" END OF BATCH SUMMARY                       ");
        System.out.println("============================================");
        System.out.println("Total Records Read    : " + totalRecords);
        System.out.println("Records Processed     : " + processedCount);
        System.out.println("Records Skipped       : " + skippedCount);
        System.out.println("Total Premiums        : $" + totalPremiums);
        System.out.println("Total Loans           : $" + totalLoans);
        System.out.println("Grand Total           : $" + (totalPremiums + totalLoans));
        System.out.println("============================================");
    }
}

class BatchRecord {

    private String policyId;
    private String name;
    private String status;
    private double amount;
    private String txnType;

    public BatchRecord(String policyId, String name, String status,
                       double amount, String txnType) {
        this.policyId = policyId;
        this.name = name;
        this.status = status;
        this.amount = amount;
        this.txnType = txnType;
    }

    public String getPolicyId() { return policyId; }
    public String getName()     { return name; }
    public String getStatus()   { return status; }
    public double getAmount()   { return amount; }
    public String getTxnType()  { return txnType; }
}

// Expected Output:
// =============================================================================
// ============================================
//  BATCH PROCESSING REPORT
// ============================================
//
// --- Processing Records ---
//
// PROC: POL001 | JOHN SMITH | PREMIUM | $1500.0
// PROC: POL002 | JANE DOE | LOAN | $5000.0
// SKIP: POL003 | BOB WILSON | Status: LAPSED
// PROC: POL004 | ANN TAYLOR | PREMIUM | $2000.0
// SKIP: POL005 | TOM BROWN | Status: CANCELLED
// PROC: POL006 | SUE DAVIS | PREMIUM | $3500.0
//
// ============================================
//  END OF BATCH SUMMARY
// ============================================
// Total Records Read    : 6
// Records Processed     : 4
// Records Skipped       : 2
// Total Premiums        : $7000.0
// Total Loans           : $5000.0
// Grand Total           : $12000.0
// ============================================
// =============================================================================
