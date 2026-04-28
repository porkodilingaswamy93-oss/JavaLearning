// =============================================================================
// Phase 1, Example 2: Enhanced For-Each, Nested Loops, Break, Continue
// =============================================================================
// Mainframe Mapping:
//   OCCURS table iteration  -->  enhanced for-each
//   Nested PERFORM          -->  nested for loops
//   EXIT PERFORM / GO TO    -->  break
//   NEXT SENTENCE           -->  continue
//
// Compile: javac Example2_EnhancedAndNested.java
// Run:     java Example2_EnhancedAndNested
// =============================================================================

public class Example2_EnhancedAndNested {

    public static void main(String[] args) {

        // -----------------------------------------------------------------
        // 1. ENHANCED FOR LOOP (for-each) — OCCURS table iteration
        // -----------------------------------------------------------------
        // COBOL:
        //   01 WS-POLICY-TABLE.
        //      05 WS-POLICY-ID OCCURS 4 TIMES PIC X(10).
        //   PERFORM VARYING WS-IDX FROM 1 BY 1 UNTIL WS-IDX > 4
        //       DISPLAY WS-POLICY-ID(WS-IDX)
        //   END-PERFORM

        System.out.println("=== ENHANCED FOR LOOP (OCCURS TABLE) ===");
        String[] policyIds = {"POL001", "POL002", "POL003", "POL004"};
        for (String policyId : policyIds) {
            System.out.println("Policy: " + policyId);
        }


        // -----------------------------------------------------------------
        // 2. NESTED LOOPS — Equivalent to nested PERFORM
        // -----------------------------------------------------------------
        // COBOL:
        //   PERFORM VARYING WS-AGENT FROM 1 BY 1 UNTIL WS-AGENT > 2
        //       PERFORM VARYING WS-POLICY FROM 1 BY 1 UNTIL WS-POLICY > 3
        //           DISPLAY 'Agent:' WS-AGENT ' Policy:' WS-POLICY
        //       END-PERFORM
        //   END-PERFORM

        System.out.println("\n=== NESTED LOOPS (NESTED PERFORM) ===");
        for (int agent = 1; agent <= 2; agent++) {
            for (int policy = 1; policy <= 3; policy++) {
                System.out.println("Agent: " + agent + " Policy: " + policy);
            }
        }


        // -----------------------------------------------------------------
        // 3. BREAK — Equivalent to EXIT PERFORM
        // -----------------------------------------------------------------
        System.out.println("\n=== BREAK (EXIT PERFORM) ===");
        String[] statuses = {"ACTIVE", "ACTIVE", "LAPSED", "ACTIVE"};
        for (String status : statuses) {
            if (status.equals("LAPSED")) {
                System.out.println("Found LAPSED policy — stopping processing.");
                break; // Exit the loop entirely
            }
            System.out.println("Processing: " + status);
        }


        // -----------------------------------------------------------------
        // 4. CONTINUE — Equivalent to SKIP / NEXT SENTENCE
        // -----------------------------------------------------------------
        System.out.println("\n=== CONTINUE (SKIP RECORD) ===");
        int[] premiums = {500, 0, 1200, 0, 800};
        for (int premium : premiums) {
            if (premium == 0) {
                continue; // Skip zero-premium records
            }
            System.out.println("Premium to process: $" + premium);
        }
    }
}

// Expected Output:
// =============================================================================
// === ENHANCED FOR LOOP (OCCURS TABLE) ===
// Policy: POL001
// Policy: POL002
// Policy: POL003
// Policy: POL004
//
// === NESTED LOOPS (NESTED PERFORM) ===
// Agent: 1 Policy: 1
// Agent: 1 Policy: 2
// Agent: 1 Policy: 3
// Agent: 2 Policy: 1
// Agent: 2 Policy: 2
// Agent: 2 Policy: 3
//
// === BREAK (EXIT PERFORM) ===
// Processing: ACTIVE
// Processing: ACTIVE
// Found LAPSED policy — stopping processing.
//
// === CONTINUE (SKIP RECORD) ===
// Premium to process: $500
// Premium to process: $1200
// Premium to process: $800
// =============================================================================
