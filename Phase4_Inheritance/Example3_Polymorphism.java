// =============================================================================
// Phase 4, Example 3: Polymorphism
// =============================================================================
// One type (parent), many forms (children). Java automatically calls
// the correct overridden method based on the actual object type.
//
// Like a batch job that reads different policy types from the same file
// and processes each one differently — without checking the type.
//
// Compile: javac Example3_Polymorphism.java
// Run:     java Example3_Polymorphism
// =============================================================================

public class Example3_Polymorphism {

    public static void main(String[] args) {

        System.out.println("=== POLYMORPHISM (PROCESS ANY POLICY) ===\n");

        // Parent type array can hold ANY child object
        InsPolicy[] policies = new InsPolicy[4];
        policies[0] = new LifeInsPolicy("LP001", "JOHN SMITH", 100000.00);
        policies[1] = new AutoInsPolicy("AP001", "BOB WILSON", 25000.00);
        policies[2] = new LifeInsPolicy("LP002", "ANN TAYLOR", 200000.00);
        policies[3] = new AutoInsPolicy("AP002", "SUE DAVIS", 50000.00);

        // Same method call — Java automatically calls the correct version
        double totalPremium = 0;
        for (InsPolicy pol : policies) {
            double premium = pol.calculatePremium();
            totalPremium += premium;
            System.out.println(pol.getPolicyId()
                + " | Type: " + pol.getPolicyType()
                + " | Sum: $" + pol.getSumAssured()
                + " | Premium: $" + premium);
        }

        System.out.println("\nTotal Premium for all policies: $" + totalPremium);
    }
}

class InsPolicy {
    private String policyId;
    private String insuredName;
    private double sumAssured;

    public InsPolicy(String policyId, String insuredName, double sumAssured) {
        this.policyId = policyId;
        this.insuredName = insuredName;
        this.sumAssured = sumAssured;
    }

    public String getPolicyId()    { return policyId; }
    public String getInsuredName() { return insuredName; }
    public double getSumAssured()  { return sumAssured; }

    public double calculatePremium() { return sumAssured * 0.03; }
    public String getPolicyType()    { return "GENERIC"; }
}

class LifeInsPolicy extends InsPolicy {

    public LifeInsPolicy(String policyId, String insuredName, double sumAssured) {
        super(policyId, insuredName, sumAssured);
    }

    @Override
    public double calculatePremium() { return getSumAssured() * 0.05; }

    @Override
    public String getPolicyType() { return "LIFE"; }
}

class AutoInsPolicy extends InsPolicy {

    public AutoInsPolicy(String policyId, String insuredName, double sumAssured) {
        super(policyId, insuredName, sumAssured);
    }

    @Override
    public double calculatePremium() { return getSumAssured() * 0.05; }

    @Override
    public String getPolicyType() { return "AUTO"; }
}

// Expected Output:
// =============================================================================
// === POLYMORPHISM (PROCESS ANY POLICY) ===
//
// LP001 | Type: LIFE | Sum: $100000.0 | Premium: $5000.0
// AP001 | Type: AUTO | Sum: $25000.0 | Premium: $1250.0
// LP002 | Type: LIFE | Sum: $200000.0 | Premium: $10000.0
// AP002 | Type: AUTO | Sum: $50000.0 | Premium: $2500.0
//
// Total Premium for all policies: $18750.0
// =============================================================================
