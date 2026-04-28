// =============================================================================
// Phase 3, Example 3: Payment Record POJO (OneInc/Transamerica Domain)
// =============================================================================
// A domain object with business methods — like a COBOL paragraph that
// processes a payment record.
//
// Compile: javac Example3_PaymentRecord.java
// Run:     java Example3_PaymentRecord
// =============================================================================

public class Example3_PaymentRecord {

    public static void main(String[] args) {

        System.out.println("=== PAYMENT RECORD POJO ===\n");

        // Create and populate using setters (like MOVE statements)
        PaymentRecord payment = new PaymentRecord();
        payment.setPolicyNumber("POL001");
        payment.setTransactionType("PREMIUM");
        payment.setPaymentAmount(1500.00);
        payment.setPaymentMethod("EFT");
        payment.setProcessingStatus("PENDING");

        System.out.println(payment);

        // Call business method (like PERFORM PROCESS-PAYMENT)
        payment.processPayment();
        System.out.println("After processing: " + payment.getProcessingStatus());

        System.out.println("\n=== MULTIPLE PAYMENTS ===\n");

        PaymentRecord[] payments = {
            new PaymentRecord("POL001", "PREMIUM", 1500.00, "EFT"),
            new PaymentRecord("POL002", "LOAN", 5000.00, "CHECK"),
            new PaymentRecord("POL003", "REINSTATEMENT", 800.00, "CREDIT_CARD")
        };

        double totalAmount = 0;
        for (PaymentRecord pmt : payments) {
            pmt.processPayment();
            totalAmount += pmt.getPaymentAmount();
        }

        System.out.println("\n--- Payment Summary ---");
        System.out.println("Total Payments: " + payments.length);
        System.out.println("Total Amount:   $" + totalAmount);
    }
}

// COBOL Copybook equivalent (PAYREC.CPY):
//   01 WS-PAYMENT-RECORD.
//      05 WS-POLICY-NUMBER      PIC X(10).
//      05 WS-TXN-TYPE           PIC X(15).
//      05 WS-PAY-AMOUNT         PIC 9(09)V99.
//      05 WS-PAY-METHOD         PIC X(10).
//      05 WS-PROC-STATUS        PIC X(10).

class PaymentRecord {

    private String policyNumber;
    private String transactionType;   // PREMIUM, LOAN, REINSTATEMENT
    private double paymentAmount;
    private String paymentMethod;     // EFT, CHECK, CREDIT_CARD
    private String processingStatus;  // PENDING, COMPLETED, FAILED

    public PaymentRecord() {
        this.processingStatus = "PENDING";
    }

    public PaymentRecord(String policyNumber, String transactionType,
                         double paymentAmount, String paymentMethod) {
        this.policyNumber = policyNumber;
        this.transactionType = transactionType;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.processingStatus = "PENDING";
    }

    // Getters
    public String getPolicyNumber()     { return policyNumber; }
    public String getTransactionType()  { return transactionType; }
    public double getPaymentAmount()    { return paymentAmount; }
    public String getPaymentMethod()    { return paymentMethod; }
    public String getProcessingStatus() { return processingStatus; }

    // Setters
    public void setPolicyNumber(String policyNumber)        { this.policyNumber = policyNumber; }
    public void setTransactionType(String transactionType)  { this.transactionType = transactionType; }
    public void setPaymentAmount(double paymentAmount)      { this.paymentAmount = paymentAmount; }
    public void setPaymentMethod(String paymentMethod)      { this.paymentMethod = paymentMethod; }
    public void setProcessingStatus(String processingStatus){ this.processingStatus = processingStatus; }

    // Business method — like PERFORM PROCESS-PAYMENT paragraph
    public void processPayment() {
        System.out.println("Processing " + transactionType + " payment of $"
                           + paymentAmount + " for policy " + policyNumber
                           + " via " + paymentMethod);
        this.processingStatus = "COMPLETED";
    }

    @Override
    public String toString() {
        return "PaymentRecord{policy='" + policyNumber + "', type='" + transactionType
             + "', amount=" + paymentAmount + ", method='" + paymentMethod
             + "', status='" + processingStatus + "'}";
    }
}

// Expected Output:
// =============================================================================
// === PAYMENT RECORD POJO ===
//
// PaymentRecord{policy='POL001', type='PREMIUM', amount=1500.0, method='EFT',
//   status='PENDING'}
// Processing PREMIUM payment of $1500.0 for policy POL001 via EFT
// After processing: COMPLETED
//
// === MULTIPLE PAYMENTS ===
//
// Processing PREMIUM payment of $1500.0 for policy POL001 via EFT
// Processing LOAN payment of $5000.0 for policy POL002 via CHECK
// Processing REINSTATEMENT payment of $800.0 for policy POL003 via CREDIT_CARD
//
// --- Payment Summary ---
// Total Payments: 3
// Total Amount:   $7300.0
// =============================================================================
