// =============================================================================
// Phase 5, Example 4: Sorting (JCL SORT Equivalent)
// =============================================================================
// Collections.sort() = SORT utility in JCL
// Comparator lambda = SORT FIELDS specification
//
// Compile: javac Example4_Sorting.java
// Run:     java Example4_Sorting
// =============================================================================

import java.util.ArrayList;
import java.util.Collections;

public class Example4_Sorting {

    public static void main(String[] args) {

        // Sort strings (like SORT FIELDS=(1,30,CH,A))
        System.out.println("=== SORT STRINGS (Ascending) ===\n");

        ArrayList<String> names = new ArrayList<>();
        names.add("WILSON");
        names.add("SMITH");
        names.add("TAYLOR");
        names.add("DOE");

        System.out.println("Before sort: " + names);
        Collections.sort(names);
        System.out.println("After sort:  " + names);


        // Sort objects by a field (like SORT FIELDS=(31,9,ZD,D))
        System.out.println("\n=== SORT OBJECTS BY FIELD ===\n");

        ArrayList<SortablePolicy> policies = new ArrayList<>();
        policies.add(new SortablePolicy("POL001", "JOHN SMITH", 50000.00));
        policies.add(new SortablePolicy("POL002", "JANE DOE", 75000.00));
        policies.add(new SortablePolicy("POL003", "BOB WILSON", 30000.00));
        policies.add(new SortablePolicy("POL004", "ANN TAYLOR", 100000.00));

        // Sort by sumAssured descending (highest first)
        Collections.sort(policies, (p1, p2) ->
            Double.compare(p2.getSumAssured(), p1.getSumAssured()));

        System.out.println("Sorted by sum assured (descending):");
        for (SortablePolicy pol : policies) {
            System.out.println("  " + pol.getPolicyId() + " | $" + pol.getSumAssured()
                + " | " + pol.getName());
        }

        // Sort by name ascending
        Collections.sort(policies, (p1, p2) ->
            p1.getName().compareTo(p2.getName()));

        System.out.println("\nSorted by name (ascending):");
        for (SortablePolicy pol : policies) {
            System.out.println("  " + pol.getName() + " | " + pol.getPolicyId());
        }
    }
}

class SortablePolicy {
    private String policyId;
    private String name;
    private double sumAssured;

    public SortablePolicy(String policyId, String name, double sumAssured) {
        this.policyId = policyId;
        this.name = name;
        this.sumAssured = sumAssured;
    }

    public String getPolicyId()   { return policyId; }
    public String getName()       { return name; }
    public double getSumAssured() { return sumAssured; }
}

// Expected Output:
// =============================================================================
// === SORT STRINGS (Ascending) ===
//
// Before sort: [WILSON, SMITH, TAYLOR, DOE]
// After sort:  [DOE, SMITH, TAYLOR, WILSON]
//
// === SORT OBJECTS BY FIELD ===
//
// Sorted by sum assured (descending):
//   POL004 | $100000.0 | ANN TAYLOR
//   POL002 | $75000.0 | JANE DOE
//   POL001 | $50000.0 | JOHN SMITH
//   POL003 | $30000.0 | BOB WILSON
//
// Sorted by name (ascending):
//   ANN TAYLOR | POL004
//   BOB WILSON | POL003
//   JANE DOE | POL002
//   JOHN SMITH | POL001
// =============================================================================
