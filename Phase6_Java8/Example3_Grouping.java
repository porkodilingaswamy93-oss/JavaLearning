// =============================================================================
// Phase 6, Example 3: Grouping (Control Break Equivalent)
// =============================================================================
// Collectors.groupingBy() does in one line what takes 20+ lines in COBOL
// control break logic.
//
// Compile: javac Example3_Grouping.java
// Run:     java Example3_Grouping
// =============================================================================

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example3_Grouping {

    public static void main(String[] args) {

        List<GrpPolicy> policies = new ArrayList<>();
        policies.add(new GrpPolicy("POL001", "JOHN SMITH", 50000, "ACTIVE", "LIFE"));
        policies.add(new GrpPolicy("POL002", "JANE DOE", 75000, "ACTIVE", "LIFE"));
        policies.add(new GrpPolicy("POL003", "BOB WILSON", 30000, "LAPSED", "AUTO"));
        policies.add(new GrpPolicy("POL004", "ANN TAYLOR", 100000, "ACTIVE", "LIFE"));
        policies.add(new GrpPolicy("POL005", "TOM BROWN", 25000, "CANCELLED", "AUTO"));
        policies.add(new GrpPolicy("POL006", "SUE DAVIS", 60000, "ACTIVE", "HEALTH"));


        // Group by status
        System.out.println("=== GROUP BY STATUS ===\n");

        Map<String, List<GrpPolicy>> byStatus = policies.stream()
            .collect(Collectors.groupingBy(p -> p.getStatus()));

        for (Map.Entry<String, List<GrpPolicy>> group : byStatus.entrySet()) {
            System.out.println("Status: " + group.getKey()
                + " (" + group.getValue().size() + " policies)");
            for (GrpPolicy p : group.getValue()) {
                System.out.println("    " + p.getId() + " | " + p.getName());
            }
        }


        // Group by type and sum amounts
        System.out.println("\n=== SUM ASSURED BY TYPE ===\n");

        Map<String, Double> sumByType = policies.stream()
            .collect(Collectors.groupingBy(
                p -> p.getType(),
                Collectors.summingDouble(p -> p.getSumAssured())
            ));

        sumByType.forEach((type, sum) ->
            System.out.println("  " + type + ": $" + sum));


        // Count by status
        System.out.println("\n=== COUNT BY STATUS ===\n");

        Map<String, Long> countByStatus = policies.stream()
            .collect(Collectors.groupingBy(
                p -> p.getStatus(),
                Collectors.counting()
            ));

        countByStatus.forEach((status, count) ->
            System.out.println("  " + status + ": " + count));
    }
}

class GrpPolicy {
    private String id;
    private String name;
    private double sumAssured;
    private String status;
    private String type;

    public GrpPolicy(String id, String name, double sumAssured,
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
// === GROUP BY STATUS ===
//
// Status: ACTIVE (4 policies)
//     POL001 | JOHN SMITH
//     POL002 | JANE DOE
//     POL004 | ANN TAYLOR
//     POL006 | SUE DAVIS
// Status: LAPSED (1 policies)
//     POL003 | BOB WILSON
// Status: CANCELLED (1 policies)
//     POL005 | TOM BROWN
//
// === SUM ASSURED BY TYPE ===
//
//   LIFE: $225000.0
//   AUTO: $55000.0
//   HEALTH: $60000.0
//
// === COUNT BY STATUS ===
//
//   ACTIVE: 4
//   LAPSED: 1
//   CANCELLED: 1
// =============================================================================
