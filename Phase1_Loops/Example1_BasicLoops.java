// =============================================================================
// Phase 1, Example 1: Basic Loops (for, while, do-while)
// =============================================================================
// Mainframe Mapping:
//   PERFORM VARYING  -->  for loop
//   PERFORM UNTIL    -->  while loop
//   PERFORM WITH TEST AFTER  -->  do-while loop
//
// Compile: javac Example1_BasicLoops.java
// Run:     java Example1_BasicLoops
// =============================================================================

public class Example1_BasicLoops {

    public static void main(String[] args) {

        // -----------------------------------------------------------------
        // 1. FOR LOOP — Equivalent to PERFORM VARYING
        // -----------------------------------------------------------------
        // COBOL:
        //   PERFORM VARYING WS-INDEX FROM 1 BY 1 UNTIL WS-INDEX > 5
        //       DISPLAY 'Policy Number: ' WS-INDEX
        //   END-PERFORM

        System.out.println("=== FOR LOOP (PERFORM VARYING) ===");
        for (int index = 1; index <= 5; index++) {
            System.out.println("Policy Number: " + index);
        }
        // Key difference: In COBOL, WS-INDEX exists in WORKING-STORAGE.
        // In Java, 'int index' is scoped to the loop and dies after it.


        // -----------------------------------------------------------------
        // 2. WHILE LOOP — Equivalent to PERFORM UNTIL (TEST BEFORE)
        // -----------------------------------------------------------------
        // COBOL:
        //   MOVE 'N' TO WS-EOF-FLAG
        //   PERFORM UNTIL WS-EOF-FLAG = 'Y'
        //       READ POLICY-FILE INTO WS-POLICY-REC
        //           AT END MOVE 'Y' TO WS-EOF-FLAG
        //           NOT AT END PERFORM PROCESS-POLICY
        //       END-READ
        //   END-PERFORM

        System.out.println("\n=== WHILE LOOP (PERFORM UNTIL) ===");
        int recordCount = 0;
        int totalRecords = 3; // Simulating 3 records in a file
        while (recordCount < totalRecords) {
            recordCount++;
            System.out.println("Processing policy record: " + recordCount);
        }
        System.out.println("End of file reached. Total processed: " + recordCount);


        // -----------------------------------------------------------------
        // 3. DO-WHILE LOOP — Equivalent to PERFORM WITH TEST AFTER
        // -----------------------------------------------------------------
        // COBOL:
        //   PERFORM WITH TEST AFTER UNTIL WS-ATTEMPT >= 3
        //       ADD 1 TO WS-ATTEMPT
        //       DISPLAY 'Login attempt: ' WS-ATTEMPT
        //   END-PERFORM
        //
        // The body runs AT LEAST ONCE, then checks the condition.

        System.out.println("\n=== DO-WHILE LOOP (TEST AFTER) ===");
        int attempt = 1;
        do {
            System.out.println("Login attempt: " + attempt);
            attempt++;
        } while (attempt <= 3);
        System.out.println("Max attempts reached.");
    }
}

// Expected Output:
// =============================================================================
// === FOR LOOP (PERFORM VARYING) ===
// Policy Number: 1
// Policy Number: 2
// Policy Number: 3
// Policy Number: 4
// Policy Number: 5
//
// === WHILE LOOP (PERFORM UNTIL) ===
// Processing policy record: 1
// Processing policy record: 2
// Processing policy record: 3
// End of file reached. Total processed: 3
//
// === DO-WHILE LOOP (TEST AFTER) ===
// Login attempt: 1
// Login attempt: 2
// Login attempt: 3
// Max attempts reached.
// =============================================================================
