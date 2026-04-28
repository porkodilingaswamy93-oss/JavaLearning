// =============================================================================
// Phase 3, Example 2: Processing Multiple Policy Records
// =============================================================================
// Demonstrates creating an array of POJO objects and processing them
// like a COBOL batch program reading records from a file.
//
// Compile: javac Example2_PolicyRecord.java
// Run:     java Example2_PolicyRecord
// =============================================================================

public class Example2_PolicyRecord {

    public static void main(String[] args) {

        System.out.println("=== BATCH PROCESSING MULTIPLE RECORDS ===\n");

        // Create an array of policy records (like reading from a file)
        PolicyData[] policies = {
            new PolicyData("POL001", "JOHN SMITH", 50000.00, "ACTIVE", "WL20", 20),
            new PolicyData("POL002", "JANE DOE", 75000.00, "ACTIVE", "TL30", 30),
            new PolicyData("POL003", "BOB WILSON", 30000.00, "LAPSED", "EL15", 15),
            new PolicyData("POL004", "ANN TAYLOR", 100000.00, "ACTIVE", "WL25", 25)
        };

        // Process like a batch program
        double totalSumAssured = 0;
        int activeCount = 0;

        for (PolicyData pol : policies) {
            if (pol.getPolicyStatus().equals("ACTIVE")) {
                activeCount++;
                totalSumAssured += pol.getSumAssured();
                System.out.println("Active: " + pol.getPolicyId()
                    + " | " + pol.getInsuredName()
                    + " | $" + pol.getSumAssured());
            } else {
                System.out.println("Skipped: " + pol.getPolicyId()
                    + " (Status: " + pol.getPolicyStatus() + ")");
            }
        }

        System.out.println("\n--- Summary ---");
        System.out.println("Active Policies   : " + activeCount);
        System.out.println("Total Sum Assured  : $" + totalSumAssured);
    }
}

class PolicyData {

    private String policyId;
    private String insuredName;
    private String policyStatus;
    private double sumAssured;
    private String planCode;
    private int termYears;

    public PolicyData(String policyId, String insuredName, double sumAssured,
                      String policyStatus, String planCode, int termYears) {
        this.policyId = policyId;
        this.insuredName = insuredName;
        this.sumAssured = sumAssured;
        this.policyStatus = policyStatus;
        this.planCode = planCode;
        this.termYears = termYears;
    }

    public String getPolicyId()      { return policyId; }
    public String getInsuredName()   { return insuredName; }
    public String getPolicyStatus()  { return policyStatus; }
    public double getSumAssured()    { return sumAssured; }
    public String getPlanCode()      { return planCode; }
    public int getTermYears()        { return termYears; }

    @Override
    public String toString() {
        return "PolicyData{" + policyId + ", " + insuredName + ", " + policyStatus
             + ", $" + sumAssured + "}";
    }
}

// Expected Output:
// =============================================================================
// === BATCH PROCESSING MULTIPLE RECORDS ===
//
// Active: POL001 | JOHN SMITH | $50000.0
// Active: POL002 | JANE DOE | $75000.0
// Skipped: POL003 (Status: LAPSED)
// Active: POL004 | ANN TAYLOR | $100000.0
//
// --- Summary ---
// Active Policies   : 3
// Total Sum Assured  : $225000.0
// =============================================================================
