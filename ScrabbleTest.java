import java.io.*;

public class ScrabbleTest {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a specific test to run: isWordInDictionary, wordScore, createHand, playHand, playHandWithMockInput, or playGameWithMockInput.");
            return;
        }
        // Run specific test based on argument
        switch (args[0]) {
            case "isWordInDictionary": testIsWordInDictionary(); break;
            case "wordScore": testWordScore(); break;
            case "createHand": testCreateHand(); break;
            case "playHand": testPlayHand(); break;
            case "playHandWithMockInput": testPlayHandWithMockInput(); break;
            case "playGameWithMockInput": testPlayGameWithMockInput(); break;
            default: System.out.println("Unknown test: " + args[0]);
        }
    }

    private static void testIsWordInDictionary() {
        System.out.println("\nTesting isWordInDictionary():");
        if (Scrabble.NUM_OF_WORDS == 0) Scrabble.init();
    
        // Test cases
        try {
            System.out.println("'' -> " + Scrabble.isWordInDictionary("") + " (expected: false)");
            System.out.println("'CAT' -> " + Scrabble.isWordInDictionary("CAT") + " (expected: false)");
            System.out.println("'xyz123' -> " + Scrabble.isWordInDictionary("xyz123") + " (expected: false)");
            System.out.println("'qwxz' -> " + Scrabble.isWordInDictionary("qwxz") + " (expected: false)");
        } catch (Exception e) {
            System.err.println("Error during test execution: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testWordScore() {
        System.out.println("\nTesting wordScore():");
        
        // Regular words
        System.out.println("'cat' -> " + Scrabble.wordScore("cat") + " (expected: 15)"); // (c=3, a=1, t=1) * 3
        System.out.println("'dog' -> " + Scrabble.wordScore("dog") + " (expected: 15)"); // (d=2, o=1, g=2) * 3
        
        // Words with high-value letters
        System.out.println("'quiz' -> " + Scrabble.wordScore("quiz") + " (expected: 88)"); // (q=10, u=1, i=1, z=10) * 4
        
        // Word with length = HAND_SIZE (bonus 50)
        String tenLetterWord = "friendship"; // (f=4, r=1, i=1, e=1, n=1, d=2, s=1, h=4, i=1, p=3) * 10 + 50 bonus
        System.out.println("'" + tenLetterWord + "' -> " + Scrabble.wordScore(tenLetterWord) + " (expected: 240)");
        
        // Word containing "runi" (bonus 1000)
        System.out.println("'running' -> " + Scrabble.wordScore("running") + " (expected: 1056)"); // (r=1, u=1, n=1, n=1, i=1, n=1, g=2) * 7  + 1000 bonus
        
        // Edge cases
        System.out.println("'' -> " + Scrabble.wordScore("") + " (expected: 0)");
        System.out.println("'a' -> " + Scrabble.wordScore("a") + " (expected: 1)");
    }

    private static void testCreateHand() {
        System.out.println("\nTesting createHand():");
        
        // Test multiple hands
        for (int i = 0; i < 3; i++) {
            String hand = Scrabble.createHand("n");
            System.out.println("\nHand " + (i+1) + ":");
            
            // Validate each hand
            System.out.println("Length: " + hand.length() + " (expected: " + Scrabble.HAND_SIZE + ")");
            System.out.println("Contains 'a': " + hand.contains("a") + " (expected: true)");
            System.out.println("Contains 'e': " + hand.contains("e") + " (expected: true)");
            
            // Verify all characters are lowercase letters
            boolean allLetters = hand.chars().allMatch(c -> c >= 'a' && c <= 'z');
            System.out.println("All lowercase letters: " + allLetters + " (expected: true)");
            
            // Verify hand contains only valid Scrabble letters
            boolean validScrabbleLetters = hand.matches("[a-z]+");
            System.out.println("Valid Scrabble letters: " + validScrabbleLetters + " (expected: true)");
        }
    }

    private static void testPlayHand() {
        System.out.println("\nTesting playHand():");
        if (Scrabble.NUM_OF_WORDS == 0) Scrabble.init();
        
        // Mock input with "." to end the hand
        String mockInputStr = ".\n";
        ByteArrayInputStream mockInput = new ByteArrayInputStream(mockInputStr.getBytes());
        InputStream originalIn = System.in;
        System.setIn(mockInput);
        
        try {
            Scrabble.playHand("test");
            System.out.println("playHand() method exists and accepts String parameter");
            
            // Test required method calls
            String hand = "cat";
            MyString.spacedString(hand);
            MyString.subsetOf("cat", hand);
            MyString.remove(hand, "cat");
            Scrabble.isWordInDictionary("cat");
            Scrabble.wordScore("cat");
            System.out.println("All required helper methods are implemented");
        } catch (Exception e) {
            System.out.println("Error in test: " + e.getMessage());
        } finally {
            System.setIn(originalIn);
        }
    }

    private static void testPlayHandWithMockInput() {
        System.out.println("\nTesting playHand with mock input (hand: aretiin):");
        Scrabble.init();
        // Test with multiple valid words that can be made from "aretiin"
        String mockInputStr = "train\ninvalid1\ninvalid2\n.\n";
        ByteArrayInputStream mockInput = new ByteArrayInputStream(mockInputStr.getBytes());
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setIn(mockInput);
        System.setOut(new PrintStream(outContent));
        
        try {
            Scrabble.playHand("aretiin");
            
            // Verify output
            String output = outContent.toString();
            System.setOut(originalOut); // Reset before printing results
            
            System.out.println("Expected sequence of plays:");
            System.out.println("1. 'train' -> score: 25");
            System.out.println("\nActual output:");
            System.out.println(output);
            
            // Optional: Add specific assertions
            boolean hasExpectedOutput = output.contains("train");
            System.out.println("\nTest passed: " + hasExpectedOutput);
            
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }

    private static void testPlayGameWithMockInput() {
        System.out.println("\nTesting playGame exists:");
        
        // Mock input with "e" to end game immediately
        String mockInputStr = "e\n";
        ByteArrayInputStream mockInput = new ByteArrayInputStream(mockInputStr.getBytes());
        InputStream originalIn = System.in;
        
        try {
            System.setIn(mockInput);
            Scrabble.playGame();
            System.out.println("playGame() method exists and can be called");
        } catch (Exception e) {
            System.out.println("Error: playGame() method is not properly implemented");
            e.printStackTrace();
        } finally {
            System.setIn(originalIn);
        }
    }
} 