// =============================================================================
// Phase 6, Example 2: Stream Basics (filter, map, sum, count, sorted)
// =============================================================================
// Stream = data processing pipeline (like JCL SORT with INCLUDE + OUTREC + SUM)
//
// Compile: javac Example2_StreamBasics.java
// Run:     java Example2_StreamBasics
// =============================================================================

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Example2_StreamBasics {

    public static void main(String[] args) {

        List<InsurancePolicy> policies = new ArrayList<>();
        policies.add(new InsurancePolicy("POL001", "JOHN SMITH", 50000, "ACTIVE", "LIFE"));
        policies.add(new InsurancePolicy("POL002", "JANE DOE", 75000, "ACTIVE", "LIFE"));
        policies.add(new InsurancePolicy("POL003", "BOB WILSON", 30000, "LAPSED", "AUTO"));
        policies.add(new InsurancePolicy("POL004", "ANN TAYLOR", 100000, "ACTIVE", "LIFE"));
        policies.add(new InsurancePolicy("POL005", "TOM BROWN", 25000, "CANCELLED", "AUTO"));
        policies.add(new InsurancePolicy("POL006", "SUE DAVIS", 60000, "ACTIVE", "HEALTH"));


        // --- FILTER: Like INCLUDE COND= in SORT ---
        System.out.println("=== FILTER (INCLUDE COND) ===\n");

        List<InsurancePolicy> activePolicies = policies.stream()
            .filter(p -> p.getStatus().equals("ACTIVE"))
            .collect(Collectors.toList());

        activePolicies.forEach(p ->
            System.out.println("  " + p.getId() + " | " + p.getName()));


        // --- MAP: Like OUTREC FIELDS= (transform/extract) ---
        System.out.println("\n=== MAP (OUTREC - extract names) ===\n");

        List<String> activeNames = policies.stream()
            .filter(p -> p.getStatus().equals("ACTIVE"))
            .map(p -> p.getName())
            .collect(Collectors.toList());

        System.out.println("  " + activeNames);


        // --- SUM: Like SUM FIELDS= ---
        System.out.println("\n=== SUM (SUM FIELDS) ===\n");

        double totalSum = policies.stream()
            .filter(p -> p.getStatus().equals("ACTIVE"))
            .mapToDouble(p -> p.getSumAssured())
            .sum();

        System.out.println("  Total Active Sum Assured: $" + totalSum);


        // --- COUNT ---
        long activeCount = policies.stream()
            .filter(p -> p.getStatus().equals("ACTIVE"))
            .count();

        System.out.println("  Active Policy Count: " + activeCount);


        // --- SORTED: Like SORT FIELDS= ---
        System.out.println("\n=== SORTED (SORT FIELDS descending) ===\n");

        policies.stream()
            .sorted((p1, p2) -> Double.compare(p2.getSumAssured(), p1.getSumAssured()))
            .forEach(p -> System.out.println("  " + p.getId()
                + " | $" + p.getSumAssured() + " | " + p.getName()));
    }
}

class InsurancePolicy {
    private String id;
    private String name;
    private double sumAssured;
    private String status;
    private String type;

    public InsurancePolicy(String id, String name, double sumAssured,
                           String status, String type) {
        this.id = id;
        this.name = name;
        this.sumAssured = sumAssured;
        this.status = status;
        this.type = type;
    }

    public String getId()           { return id; }
    public String getName()         { return name; }
    public double getSumAssured()   { return sumAssured; }
    public String getStatus()       { return status; }
    public String getType()         { return type; }
}

// Expected Output:
// =============================================================================
// === FILTER (INCLUDE COND) ===
//
//   POL001 | JOHN SMITH
//   POL002 | JANE DOE
//   POL004 | ANN TAYLOR
//   POL006 | SUE DAVIS
//
// === MAP (OUTREC - extract names) ===
//
//   [JOHN SMITH, JANE DOE, ANN TAYLOR, SUE DAVIS]
//
// === SUM (SUM FIELDS) ===
//
//   Total Active Sum Assured: $285000.0
//   Active Policy Count: 4
//
// === SORTED (SORT FIELDS descending) ===
//
//   POL004 | $100000.0 | ANN TAYLOR
//   POL002 | $75000.0 | JANE DOE
//   POL006 | $60000.0 | SUE DAVIS
//   POL001 | $50000.0 | JOHN SMITH
//   POL003 | $30000.0 | BOB WILSON
//   POL005 | $25000.0 | TOM BROWN
// =============================================================================
