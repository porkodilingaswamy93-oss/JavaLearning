// =============================================================================
// Phase 5, Example 5: Payment Batch Processing with Collections
// =============================================================================
// Real-world: Group payments by policy and summarize — like your OneInc
// batch processing. Uses HashMap for grouping (like SORT + MERGE by key).
//
// Compile: javac Example5_PaymentBatch.java
// Run:     java Example5_PaymentBatch
// =============================================================================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Example5_PaymentBatch {

    public static void main(String[] args) {

        System.out.println("=== PAYMENT BATCH PROCESSING ===\n");

        // Incoming payment records (like reading from Data Lake file)
        List<PmtData> payments = new ArrayList<>();
        payments.add(new PmtData("POL001", "PREMIUM", 1500.00));
        payments.add(new PmtData("POL002", "LOAN", 5000.00));
        payments.add(new PmtData("POL001", "PREMIUM", 1500.00));
        payments.add(new PmtData("POL003", "REINSTATEMENT", 800.00));
        payments.add(new PmtData("POL002", "PREMIUM", 2000.00));

        // Group payments by policy (like SORT + MERGE by key)
        HashMap<String, Double> policyTotals = new HashMap<>();
        HashMap<String, Integer> policyCounts = new HashMap<>();

        for (PmtData pmt : payments) {
            String key = pmt.getPolicyId();
            policyTotals.put(key,
                policyTotals.getOrDefault(key, 0.0) + pmt.getAmount());
            policyCounts.put(key,
                policyCounts.getOrDefault(key, 0) + 1);
        }

        System.out.println("--- Payment Summary by Policy ---");
        double grandTotal = 0;
        for (String key : policyTotals.keySet()) {
            double total = policyTotals.get(key);
            grandTotal += total;
            System.out.println("Policy: " + key
                + " | Transactions: " + policyCounts.get(key)
                + " | Total: $" + total);
        }
        System.out.println("\nGrand Total: $" + grandTotal);
        System.out.println("Total Transactions: " + payments.size());
    }
}

class PmtData {
    private String policyId;
    private String type;
    private double amount;

    public PmtData(String policyId, String type, double amount) {
        this.policyId = policyId;
        this.type = type;
        this.amount = amount;
    }

    public String getPolicyId() { return policyId; }
    public String getType()     { return type; }
    public double getAmount()   { return amount; }
}

// Expected Output:
// =============================================================================
// === PAYMENT BATCH PROCESSING ===
//
// --- Payment Summary by Policy ---
// Policy: POL001 | Transactions: 2 | Total: $3000.0
// Policy: POL002 | Transactions: 2 | Total: $7000.0
// Policy: POL003 | Transactions: 1 | Total: $800.0
//
// Grand Total: $10800.0
// Total Transactions: 5
// =============================================================================
