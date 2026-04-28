// =============================================================================
// Phase 4, Example 1: Basic Inheritance
// =============================================================================
// Base class Policy + child classes LifePolicy and AutoPolicy.
// Like a base copybook with extended copybooks for each product type.
//
// Compile: javac Example1_BasicInheritance.java
// Run:     java Example1_BasicInheritance
// =============================================================================

public class Example1_BasicInheritance {

    public static void main(String[] args) {

        System.out.println("=== INHERITANCE DEMO ===\n");

        // Life Policy (child of Policy)
        LifePolicy life = new LifePolicy();
        life.setPolicyId("LP001");
        life.setInsuredName("JOHN SMITH");
        life.setSumAssured(100000.00);
        life.setNominee("JANE SMITH");
        life.setRiderType("ACCIDENTAL DEATH");

        // Auto Policy (child of Policy)
        AutoPolicy auto = new AutoPolicy();
        auto.setPolicyId("AP001");
        auto.setInsuredName("BOB WILSON");
        auto.setSumAssured(25000.00);
        auto.setVehicleNumber("TN09AB1234");
        auto.setVehicleType("SEDAN");

        // Both inherit displayDetails() from Policy, but override it
        life.displayDetails();
        System.out.println();
        auto.displayDetails();
    }
}

// Base class: Policy (parent — like the main copybook)
class Policy {
    private String policyId;
    private String insuredName;
    private double sumAssured;

    public Policy() {}

    public Policy(String policyId, String insuredName, double sumAssured) {
        this.policyId = policyId;
        this.insuredName = insuredName;
        this.sumAssured = sumAssured;
    }

    public String getPolicyId()     { return policyId; }
    public String getInsuredName()  { return insuredName; }
    public double getSumAssured()   { return sumAssured; }

    public void setPolicyId(String policyId)         { this.policyId = policyId; }
    public void setInsuredName(String insuredName)    { this.insuredName = insuredName; }
    public void setSumAssured(double sumAssured)      { this.sumAssured = sumAssured; }

    public void displayDetails() {
        System.out.println("--- Policy Details ---");
        System.out.println("Policy ID   : " + policyId);
        System.out.println("Insured     : " + insuredName);
        System.out.println("Sum Assured : $" + sumAssured);
    }
}

// Child class: LifePolicy (extends Policy, adds life-specific fields)
class LifePolicy extends Policy {

    private String nominee;
    private String riderType;

    public LifePolicy() { super(); }

    public LifePolicy(String policyId, String insuredName, double sumAssured,
                      String nominee, String riderType) {
        super(policyId, insuredName, sumAssured); // Initialize parent fields
        this.nominee = nominee;
        this.riderType = riderType;
    }

    public String getNominee()  { return nominee; }
    public String getRiderType(){ return riderType; }
    public void setNominee(String nominee)      { this.nominee = nominee; }
    public void setRiderType(String riderType)  { this.riderType = riderType; }

    @Override
    public void displayDetails() {
        super.displayDetails(); // Call parent's display first
        System.out.println("Nominee     : " + nominee);
        System.out.println("Rider       : " + riderType);
    }
}

// Child class: AutoPolicy (extends Policy, adds auto-specific fields)
class AutoPolicy extends Policy {

    private String vehicleNumber;
    private String vehicleType;

    public AutoPolicy() { super(); }

    public String getVehicleNumber() { return vehicleNumber; }
    public String getVehicleType()   { return vehicleType; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public void setVehicleType(String vehicleType)     { this.vehicleType = vehicleType; }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Vehicle No  : " + vehicleNumber);
        System.out.println("Vehicle Type: " + vehicleType);
    }
}

// Expected Output:
// =============================================================================
// === INHERITANCE DEMO ===
//
// --- Policy Details ---
// Policy ID   : LP001
// Insured     : JOHN SMITH
// Sum Assured : $100000.0
// Nominee     : JANE SMITH
// Rider       : ACCIDENTAL DEATH
//
// --- Policy Details ---
// Policy ID   : AP001
// Insured     : BOB WILSON
// Sum Assured : $25000.0
// Vehicle No  : TN09AB1234
// Vehicle Type: SEDAN
// =============================================================================
