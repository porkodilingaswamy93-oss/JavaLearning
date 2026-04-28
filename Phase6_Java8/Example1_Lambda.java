// =============================================================================
// Phase 6, Example 1: Lambda Expressions
// =============================================================================
// A lambda is an inline function — like writing PERFORM logic without
// creating a separate paragraph.
// Syntax: (parameters) -> expression
//
// Compile: javac Example1_Lambda.java
// Run:     java Example1_Lambda
// =============================================================================

import java.util.Arrays;
import java.util.List;

public class Example1_Lambda {

    public static void main(String[] args) {

        System.out.println("=== LAMBDA EXPRESSIONS ===\n");

        List<String> policyIds = Arrays.asList("POL003", "POL001", "POL004", "POL002");

        // Sort using lambda (like SORT FIELDS=(...,A))
        policyIds.sort((a, b) -> a.compareTo(b));
        System.out.println("Sorted: " + policyIds);

        // forEach with lambda
        System.out.println("\nProcessing each:");
        policyIds.forEach(id -> System.out.println("  Processing: " + id));

        // Method reference — shorthand for lambda
        // System.out::println is the same as (x) -> System.out.println(x)
        System.out.println("\nUsing method reference:");
        policyIds.forEach(System.out::println);

        // Lambda with multiple statements
        System.out.println("\nLambda with logic:");
        policyIds.forEach(id -> {
            String masked = "***" + id.substring(3);
            System.out.println("  " + id + " -> " + masked);
        });

        // Sort descending
        policyIds.sort((a, b) -> b.compareTo(a));
        System.out.println("\nSorted descending: " + policyIds);
    }
}

// Expected Output:
// =============================================================================
// === LAMBDA EXPRESSIONS ===
//
// Sorted: [POL001, POL002, POL003, POL004]
//
// Processing each:
//   Processing: POL001
//   Processing: POL002
//   Processing: POL003
//   Processing: POL004
//
// Using method reference:
// POL001
// POL002
// POL003
// POL004
//
// Lambda with logic:
//   POL001 -> ***001
//   POL002 -> ***002
//   POL003 -> ***003
//   POL004 -> ***004
//
// Sorted descending: [POL004, POL003, POL002, POL001]
// =============================================================================
