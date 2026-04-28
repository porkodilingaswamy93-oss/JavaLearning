// =============================================================================
// Phase 4, Example 4: Abstract Classes and Interfaces
// =============================================================================
// Abstract class = defines structure but forces children to implement methods.
// Interface = a contract defining what methods a class MUST have.
//
// Like a CALL interface specification — the child MUST implement the logic.
//
// Compile: javac Example4_AbstractInterface.java
// Run:     java Example4_AbstractInterface
// =============================================================================

public class Example4_AbstractInterface {

    public static void main(String[] args) {

        System.out.println("=== ABSTRACT CLASS DEMO ===\n");

        // Cannot do: Transaction t = new Transaction(); // COMPILE ERROR
        // Must use a concrete child class:
        Transaction premium = new PremiumTransaction("POL001", 1500.00);
        Transaction loan = new LoanTransaction("POL002", 5000.00);

        premium.execute();
        loan.execute();

        System.out.println("\n=== INTERFACE DEMO ===\n");

        // Both classes implement Auditable, so both have getAuditTrail()
        Auditable[] auditItems = { premium, loan };
        for (Auditable item : auditItems) {
            System.out.println("Audit: " + item.getAuditTrail());
        }
    }
}

// Interface: a contract (like a CALL parameter specification)
interface Auditable {
    String getAuditTrail();  // Any implementing class MUST define this
}

// Abstract class: cannot be instantiated, forces children to implement execute()
abstract class Transaction implements Auditable {

    protected String policyNumber;
    protected double amount;
    protected String status;

    public Transaction(String policyNumber, double amount) {
        this.policyNumber = policyNumber;
        this.amount = amount;
        this.status = "PENDING";
    }

    // Abstract method — child MUST implement (no body here)
    public abstract void execute();

    public String getStatus() { return status; }
}

// Concrete class: PremiumTransaction
class PremiumTransaction extends Transaction {

    public PremiumTransaction(String policyNumber, double amount) {
        super(policyNumber, amount);
    }

    @Override
    public void execute() {
        System.out.println("Applying premium of $" + amount
                           + " to policy " + policyNumber);
        this.status = "PREMIUM APPLIED";
        System.out.println("  Status: " + status);
    }

    @Override
    public String getAuditTrail() {
        return "PREMIUM on " + policyNumber + " for $" + amount;
    }
}

// Concrete class: LoanTransaction
class LoanTransaction extends Transaction {

    public LoanTransaction(String policyNumber, double amount) {
        super(policyNumber, amount);
    }

    @Override
    public void execute() {
        System.out.println("Disbursing loan of $" + amount
                           + " for policy " + policyNumber);
        this.status = "LOAN DISBURSED";
        System.out.println("  Status: " + status);
    }

    @Override
    public String getAuditTrail() {
        return "LOAN on " + policyNumber + " for $" + amount;
    }
}

// Expected Output:
// =============================================================================
// === ABSTRACT CLASS DEMO ===
//
// Applying premium of $1500.0 to policy POL001
//   Status: PREMIUM APPLIED
// Disbursing loan of $5000.0 for policy POL002
//   Status: LOAN DISBURSED
//
// === INTERFACE DEMO ===
//
// Audit: PREMIUM on POL001 for $1500.0
// Audit: LOAN on POL002 for $5000.0
// =============================================================================
