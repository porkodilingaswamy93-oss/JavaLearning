// =============================================================================
// Phase 4, Example 2: Method Overriding
// =============================================================================
// Same method name, different behavior in each child class.
// Like COPY ... REPLACING a paragraph in COBOL.
//
// Compile: javac Example2_Overriding.java
// Run:     java Example2_Overriding
// =============================================================================

public class Example2_Overriding {

    public static void main(String[] args) {

        System.out.println("=== METHOD OVERRIDING (CALCULATE PREMIUM) ===\n");

        BasePolicy generic = new BasePolicy("GEN001", "GENERIC CUSTOMER", 100000.00);
        LifePol life = new LifePol("LP001", "JOHN SMITH", 100000.00);
        AutoPol auto = new AutoPol("AP001", "BOB WILSON", 25000.00);

        // Same method name — different behavior
        System.out.println(generic.getPolicyId() + " (" + generic.getPolicyType()
            + ") Premium: $" + generic.calculatePremium());

        System.out.println(life.getPolicyId() + " (" + life.getPolicyType()
            + ") Premium: $" + life.calculatePremium());

        System.out.println(auto.getPolicyId() + " (" + auto.getPolicyType()
            + ") Premium: $" + auto.calculatePremium());
    }
}

class BasePolicy {
    private String policyId;
    private String insuredName;
    private double sumAssured;

    public BasePolicy(String policyId, String insuredName, double sumAssured) {
        this.policyId = policyId;
        this.insuredName = insuredName;
        this.sumAssured = sumAssured;
    }

    public String getPolicyId()    { return policyId; }
    public String getInsuredName() { return insuredName; }
    public double getSumAssured()  { return sumAssured; }

    // Default premium calculation (3%)
    public double calculatePremium() {
        return sumAssured * 0.03;
    }

    public String getPolicyType() {
        return "GENERIC";
    }
}

class LifePol extends BasePolicy {

    public LifePol(String policyId, String insuredName, double sumAssured) {
        super(policyId, insuredName, sumAssured);
    }

    @Override
    public double calculatePremium() {
        return getSumAssured() * 0.05; // Life = 5%
    }

    @Override
    public String getPolicyType() {
        return "LIFE";
    }
}

class AutoPol extends BasePolicy {

    public AutoPol(String policyId, String insuredName, double sumAssured) {
        super(policyId, insuredName, sumAssured);
    }

    @Override
    public double calculatePremium() {
        return getSumAssured() * 0.05; // Auto = 5%
    }

    @Override
    public String getPolicyType() {
        return "AUTO";
    }
}

// Expected Output:
// =============================================================================
// === METHOD OVERRIDING (CALCULATE PREMIUM) ===
//
// GEN001 (GENERIC) Premium: $3000.0
// LP001 (LIFE) Premium: $5000.0
// AP001 (AUTO) Premium: $1250.0
// =============================================================================
