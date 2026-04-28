// =============================================================================
// Phase 6, Example 5: Real-World Payment Processing Pipeline
// =============================================================================
// Combining all Java 8 features — like your OneInc batch processing.
// filter + groupingBy + summingDouble + max + counting
//
// Compile: javac Example5_RealWorldPipeline.java
// Run:     java Example5_RealWorldPipeline
// =============================================================================

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example5_RealWorldPipeline {

    public static void main(String[] args) {

        System.out.println("=== PAYMENT PROCESSING PIPELINE ===\n");

        List<PaymentTxn> transactions = Arrays.asList(
            new PaymentTxn("POL001", "PREMIUM", 1500.00, "SUCCESS"),
            new PaymentTxn("POL002", "LOAN", 5000.00, "SUCCESS"),
            new PaymentTxn("POL001", "PREMIUM", 1500.00, "SUCCESS"),
            new PaymentTxn("POL003", "PREMIUM", 800.00, "FAILED"),
            new PaymentTxn("POL002", "PREMIUM", 2000.00, "SUCCESS"),
            new PaymentTxn("POL004", "REINSTATEMENT", 3000.00, "SUCCESS"),
            new PaymentTxn("POL003", "PREMIUM", 800.00, "SUCCESS")
        );

        // Pipeline: Filter successful -> Group by policy -> Sum amounts
        System.out.println("--- Successful Payments by Policy ---");
        Map<String, Double> successByPolicy = transactions.stream()
            .filter(t -> t.getStatus().equals("SUCCESS"))
            .collect(Collectors.groupingBy(
                t -> t.getPolicyId(),
                Collectors.summingDouble(t -> t.getAmount())
            ));

        successByPolicy.forEach((policy, total) ->
            System.out.println("  " + policy + ": $" + total));

        // Grand total
        double grandTotal = transactions.stream()
            .filter(t -> t.getStatus().equals("SUCCESS"))
            .mapToDouble(t -> t.getAmount())
            .sum();

        System.out.println("\nGrand Total (Successful): $" + grandTotal);

        // Count by status
        Map<String, Long> countByStatus = transactions.stream()
            .collect(Collectors.groupingBy(
                t -> t.getStatus(),
                Collectors.counting()
            ));

        System.out.println("\nTransaction Counts:");
        countByStatus.forEach((status, count) ->
            System.out.println("  " + status + ": " + count));

        // Find highest single transaction
        transactions.stream()
            .max((t1, t2) -> Double.compare(t1.getAmount(), t2.getAmount()))
            .ifPresent(t -> System.out.println("\nHighest Transaction: "
                + t.getPolicyId() + " | " + t.getType() + " | $" + t.getAmount()));
    }
}

class PaymentTxn {
    private String policyId;
    private String type;
    private double amount;
    private String status;

    public PaymentTxn(String policyId, String type, double amount, String status) {
        this.policyId = policyId;
        this.type = type;
        this.amount = amount;
        this.status = status;
    }

    public String getPolicyId() { return policyId; }
    public String getType()     { return type; }
    public double getAmount()   { return amount; }
    public String getStatus()   { return status; }
}

// Expected Output:
// =============================================================================
// === PAYMENT PROCESSING PIPELINE ===
//
// --- Successful Payments by Policy ---
//   POL001: $3000.0
//   POL002: $7000.0
//   POL003: $800.0
//   POL004: $3000.0
//
// Grand Total (Successful): $13800.0
//
// Transaction Counts:
//   SUCCESS: 6
//   FAILED: 1
//
// Highest Transaction: POL002 | LOAN | $5000.0
// =============================================================================
