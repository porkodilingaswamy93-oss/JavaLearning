// =============================================================================
// Phase 8: Coding Exercises for Interview Practice
// =============================================================================
// Common coding questions asked in Java interviews.
// Practice these until you can write them without looking.
//
// Compile: javac CodingExercises.java
// Run:     java CodingExercises
// =============================================================================

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.Comparator;

public class CodingExercises {

    public static void main(String[] args) {

        System.out.println("=============================================");
        System.out.println(" CODING EXERCISES FOR INTERVIEW PRACTICE     ");
        System.out.println("=============================================\n");


        // Q1: Reverse a string
        System.out.println("Q1: Reverse a String");
        String input = "POLICY";
        String reversed = new StringBuilder(input).reverse().toString();
        System.out.println("  Reverse '" + input + "' = '" + reversed + "'");


        // Q2: Check if a string is a palindrome
        System.out.println("\nQ2: Palindrome Check");
        String word = "MADAM";
        String rev = new StringBuilder(word).reverse().toString();
        System.out.println("  Is '" + word + "' palindrome? " + word.equals(rev));

        String word2 = "POLICY";
        String rev2 = new StringBuilder(word2).reverse().toString();
        System.out.println("  Is '" + word2 + "' palindrome? " + word2.equals(rev2));


        // Q3: Find duplicate characters in a string
        System.out.println("\nQ3: Duplicate Characters in 'INSURANCE'");
        String text = "INSURANCE";
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : text.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        charCount.forEach((ch, count) -> {
            if (count > 1) {
                System.out.println("  '" + ch + "' appears " + count + " times");
            }
        });


        // Q4: FizzBuzz (classic interview question)
        System.out.println("\nQ4: FizzBuzz (1-15)");
        System.out.print("  ");
        for (int i = 1; i <= 15; i++) {
            if (i % 15 == 0)      System.out.print("FizzBuzz ");
            else if (i % 3 == 0)  System.out.print("Fizz ");
            else if (i % 5 == 0)  System.out.print("Buzz ");
            else                  System.out.print(i + " ");
        }
        System.out.println();


        // Q5: Find second highest premium using streams
        System.out.println("\nQ5: Second Highest Premium");
        List<Double> premiums = Arrays.asList(1500.0, 3000.0, 800.0, 5000.0, 2200.0);
        Optional<Double> secondHighest = premiums.stream()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .findFirst();
        secondHighest.ifPresent(p -> System.out.println("  $" + p));


        // Q6: Count words in a string
        System.out.println("\nQ6: Count Words");
        String sentence = "Java is the future of mainframe modernization";
        String[] words = sentence.trim().split("\\s+");
        System.out.println("  \"" + sentence + "\"");
        System.out.println("  Word count: " + words.length);


        // Q7: Find the most frequent element
        System.out.println("\nQ7: Most Frequent Status");
        String[] statuses = {"ACTIVE", "LAPSED", "ACTIVE", "ACTIVE", "CANCELLED", "LAPSED"};
        HashMap<String, Integer> freq = new HashMap<>();
        for (String s : statuses) {
            freq.put(s, freq.getOrDefault(s, 0) + 1);
        }
        String mostFrequent = "";
        int maxCount = 0;
        for (HashMap.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        System.out.println("  Most frequent: " + mostFrequent + " (" + maxCount + " times)");


        System.out.println("\n=============================================");
        System.out.println(" END OF CODING EXERCISES                     ");
        System.out.println("=============================================");
    }
}

// Expected Output:
// =============================================================================
// =============================================
//  CODING EXERCISES FOR INTERVIEW PRACTICE
// =============================================
//
// Q1: Reverse a String
//   Reverse 'POLICY' = 'YCILOP'
//
// Q2: Palindrome Check
//   Is 'MADAM' palindrome? true
//   Is 'POLICY' palindrome? false
//
// Q3: Duplicate Characters in 'INSURANCE'
//   'N' appears 2 times
//   'A' appears 2 times  (order may vary)
//
// Q4: FizzBuzz (1-15)
//   1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz
//
// Q5: Second Highest Premium
//   $3000.0
//
// Q6: Count Words
//   "Java is the future of mainframe modernization"
//   Word count: 7
//
// Q7: Most Frequent Status
//   Most frequent: ACTIVE (3 times)
//
// =============================================
//  END OF CODING EXERCISES
// =============================================
// =============================================================================
