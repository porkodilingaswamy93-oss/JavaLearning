// =============================================================================
// Phase 6, Example 4: Optional (Safe Null Handling)
// =============================================================================
// Optional prevents NullPointerException — the #1 Java runtime error.
// Like checking IF WS-FIELD = SPACES before using it in COBOL.
//
// Compile: javac Example4_Optional.java
// Run:     java Example4_Optional
// =============================================================================

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Example4_Optional {

    public static void main(String[] args) {

        List<OptPolicy> policies = new ArrayList<>();
        policies.add(new OptPolicy("POL001", "JOHN SMITH", 50000, "ACTIVE"));
        policies.add(new OptPolicy("POL002", "JANE DOE", 75000, "ACTIVE"));
        policies.add(new OptPolicy("POL003", "BOB WILSON", 30000, "LAPSED"));
        policies.add(new OptPolicy("POL004", "ANN TAYLOR", 100000, "ACTIVE"));


        // Find first LAPSED policy
        System.out.println("=== OPTIONAL: findFirst ===\n");

        Optional<OptPolicy> lapsed = policies.stream()
            .filter(p -> p.getStatus().equals("LAPSED"))
            .findFirst();

        // Safe access with isPresent()
        if (lapsed.isPresent()) {
            System.out.println("Found lapsed: " + lapsed.get().getName());
        } else {
            System.out.println("No lapsed policies found.");
        }

        // More concise: ifPresent() with lambda
        lapsed.ifPresent(p -> System.out.println("Lapsed policy ID: " + p.getId()));


        // Find something that does NOT exist
        System.out.println("\n=== OPTIONAL: orElse (safe default) ===\n");

        Optional<OptPolicy> vip = policies.stream()
            .filter(p -> p.getSumAssured() > 500000)
            .findFirst();

        // map() + orElse() — extract value with safe fallback
        String vipName = vip.map(p -> p.getName()).orElse("NONE FOUND");
        System.out.println("VIP Policyholder: " + vipName);

        // orElse for a value that exists
        Optional<OptPolicy> active = policies.stream()
            .filter(p -> p.getStatus().equals("ACTIVE"))
            .findFirst();

        String activeName = active.map(p -> p.getName()).orElse("NONE FOUND");
        System.out.println("First Active: " + activeName);
    }
}

class OptPolicy {
    private String id;
    private String name;
    private double sumAssured;
    private String status;

    public OptPolicy(String id, String name, double sumAssured, String status) {
        this.id = id;
        this.name = name;
        this.sumAssured = sumAssured;
        this.status = status;
    }

    public String getId()           { return id; }
    public String getName()         { return name; }
    public double getSumAssured()   { return sumAssured; }
    public String getStatus()       { return status; }
}

// Expected Output:
// =============================================================================
// === OPTIONAL: findFirst ===
//
// Found lapsed: BOB WILSON
// Lapsed policy ID: POL003
//
// === OPTIONAL: orElse (safe default) ===
//
// VIP Policyholder: NONE FOUND
// First Active: JOHN SMITH
// =============================================================================
