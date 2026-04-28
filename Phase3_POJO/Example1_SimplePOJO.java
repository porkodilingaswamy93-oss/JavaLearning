// =============================================================================
// Phase 3, Example 1: Simple POJO (Copybook to Java Class)
// =============================================================================
// A POJO is the Java equivalent of a COBOL copybook.
// Each copybook field becomes a private instance variable with getter/setter.
//
// Compile: javac Example1_SimplePOJO.java
// Run:     java Example1_SimplePOJO
// =============================================================================

public class Example1_SimplePOJO {

    public static void main(String[] args) {

        System.out.println("=== CREATING A POLICY RECORD ===\n");

        // Using setters (like MOVE value TO field)
        PolicyRecord policy1 = new PolicyRecord();
        policy1.setPolicyId("POL001");
        policy1.setInsuredName("JOHN SMITH");
        policy1.setPolicyStatus("ACTIVE");
        policy1.setSumAssured(50000.00);
        policy1.setPlanCode("WL20");
        policy1.setTermYears(20);

        // Using getters (like DISPLAY field)
        System.out.println("Policy ID    : " + policy1.getPolicyId());
        System.out.println("Insured Name : " + policy1.getInsuredName());
        System.out.println("Status       : " + policy1.getPolicyStatus());
        System.out.println("Sum Assured  : $" + policy1.getSumAssured());
        System.out.println("Plan Code    : " + policy1.getPlanCode());
        System.out.println("Term (Years) : " + policy1.getTermYears());

        System.out.println("\n=== USING CONSTRUCTOR ===\n");

        // Using parameterized constructor (like INITIALIZE with VALUES)
        PolicyRecord policy2 = new PolicyRecord(
            "POL002", "JANE DOE", "ACTIVE", 75000.00, "TL30", 30
        );
        System.out.println(policy2); // Calls toString() automatically
    }
}


// ============================================================================
// POJO Class: PolicyRecord
// ============================================================================
// COBOL Copybook equivalent (POLREC.CPY):
//   01 WS-POLICY-RECORD.
//      05 WS-POLICY-ID       PIC X(10).    --> String
//      05 WS-INSURED-NAME    PIC X(30).    --> String
//      05 WS-POLICY-STATUS   PIC X(10).    --> String
//      05 WS-SUM-ASSURED     PIC 9(09)V99. --> double
//      05 WS-PLAN-CODE       PIC X(04).    --> String
//      05 WS-TERM-YEARS      PIC 9(02).    --> int
// ============================================================================

class PolicyRecord {

    // Private fields = copybook fields
    private String policyId;
    private String insuredName;
    private String policyStatus;
    private double sumAssured;
    private String planCode;
    private int termYears;

    // Default constructor (like INITIALIZE)
    public PolicyRecord() {}

    // Parameterized constructor (like MOVE values during record creation)
    public PolicyRecord(String policyId, String insuredName, String policyStatus,
                        double sumAssured, String planCode, int termYears) {
        this.policyId = policyId;
        this.insuredName = insuredName;
        this.policyStatus = policyStatus;
        this.sumAssured = sumAssured;
        this.planCode = planCode;
        this.termYears = termYears;
    }

    // Getters (like DISPLAY / reading a field value)
    public String getPolicyId()      { return policyId; }
    public String getInsuredName()   { return insuredName; }
    public String getPolicyStatus()  { return policyStatus; }
    public double getSumAssured()    { return sumAssured; }
    public String getPlanCode()      { return planCode; }
    public int getTermYears()        { return termYears; }

    // Setters (like MOVE value TO field)
    public void setPolicyId(String policyId)          { this.policyId = policyId; }
    public void setInsuredName(String insuredName)    { this.insuredName = insuredName; }
    public void setPolicyStatus(String policyStatus)  { this.policyStatus = policyStatus; }
    public void setSumAssured(double sumAssured)      { this.sumAssured = sumAssured; }
    public void setPlanCode(String planCode)          { this.planCode = planCode; }
    public void setTermYears(int termYears)           { this.termYears = termYears; }

    // toString (like a formatted DISPLAY of the entire record)
    @Override
    public String toString() {
        return "PolicyRecord{policyId='" + policyId + "', insuredName='" + insuredName
             + "', status='" + policyStatus + "', sumAssured=" + sumAssured
             + ", planCode='" + planCode + "', term=" + termYears + "}";
    }
}

// Expected Output:
// =============================================================================
// === CREATING A POLICY RECORD ===
//
// Policy ID    : POL001
// Insured Name : JOHN SMITH
// Status       : ACTIVE
// Sum Assured  : $50000.0
// Plan Code    : WL20
// Term (Years) : 20
//
// === USING CONSTRUCTOR ===
//
// PolicyRecord{policyId='POL002', insuredName='JANE DOE', status='ACTIVE',
//   sumAssured=75000.0, planCode='TL30', term=30}
// =============================================================================
