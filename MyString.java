import java.util.Random;

/**
 * A library of string functions.
 */
public class MyString {
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
        System.out.println(countChar(hello, 'z'));
        System.out.println(spacedString(hello));
        System.out.println(randomStringOfLetters(10));
        System.out.println(randomStringOfLetters(45));
        System.out.println(remove("understand","daunsed"));
        System.out.println(remove("understand","daunse"));
        System.out.println(remove("zoo","oo"));
        System.out.println(insertRandomly('t', hello));
        
        //// Put your other tests here.
    }

    /**
     * Returns the number of times the given character appears in the given string.
     * Example: countChar("Center",'e') returns 2 and countChar("Center",'c') returns 0. 
     * 
     * @param str - a string
     * @param c - a character
     * @return the number of times c appears in str
     */
    public static int countChar(String str, char ch) {
        int count = 0;
        for (char letter : str.toCharArray()) {
            if (letter == ch) count++;
        }
        return count;
    }

    /** Returns true if str1 is a subset string str2, false otherwise
     *  Examples:
     *  subsetOf("sap","space") returns true
     *  subsetOf("spa","space") returns true
     *  subsetOf("pass","space") returns false
     *  subsetOf("c","space") returns true
     *
     * @param str1 - a string
     * @param str2 - a string
     * @return true is str1 is a subset of str2, false otherwise
     */
    public static boolean subsetOf(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            char currentChar = str1.charAt(i);
            if (str2.indexOf(currentChar) == -1) {
                return false;  
            }
            str2 = str2.substring(0, str2.indexOf(currentChar)) + str2.substring(str2.indexOf(currentChar) + 1);
        }
    
        return true;
        }

    /** Returns a string which is the same as the given string, with a space
     * character inserted after each character in the given string, except
     * for the last character. 
     * Example: spacedString("silent") returns "s i l e n t"
     * 
     * @param str - a string
     * @return a string consisting of the characters of str, separated by spaces.
     */
    public static String spacedString(String str) {
        String spacedString = "";
        for (int i = 0; i < str.length() ; i++) {
            if(i == 0){
                spacedString += str.charAt(i);
            }else{
                spacedString = spacedString +" " + str.charAt(i);
            }
        }
        return spacedString;
    }
  
    /**
     * Returns a string of n lowercase letters, selected randomly from 
     * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
     * letter can be selected more than once.
     * 
     * Example: randomStringOfLetters(3) can return "zoo"
     * 
     * @param n - the number of letter to select
     * @return a randomly generated string, consisting of 'n' lowercase letters
     */
    public static String randomStringOfLetters(int n) {
        char [] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String randomLetters = "";
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int num = (random.nextInt(26));
            char randomChar = abc[num];
            randomLetters = randomLetters + String.valueOf(randomChar);
        }
        return randomLetters;
        
    }

    /**
     * Returns a string consisting of the string str1, minus all the characters in the
     * string str2. Assumes (without checking) that str2 is a subset of str1.
     * Example: remove("meet","committee") returns "comit" 
     * 
     * @param str1 - a string
     * @param str2 - a string
     * @return a string consisting of str1 minus all the characters of str2
     */
    public static boolean equals(char[] arr1, char[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }
    
    public static String remove(String str1, String str2) {
        String result = "";  
    
        for (int i = 0; i < str1.length(); i++) {
            char currentChar = str1.charAt(i);

            if (str2.indexOf(currentChar) != -1) {
                str2 = str2.substring(0, str2.indexOf(currentChar)) + str2.substring(str2.indexOf(currentChar) + 1);
            } else {
                result += currentChar;
            }
        }

        return result;
    }


    /**
     * Returns a string consisting of the given string, with the given 
     * character inserted randomly somewhere in the string.
     * For example, insertRandomly("s","cat") can return "scat", or "csat", or "cast", or "cats".  
     * @param ch - a character
     * @param str - a string
     * @return a string consisting of str with ch inserted somewhere
     */
    public static String insertRandomly(char ch, String str) {
        String randomInsert = new String();
        Random random1 = new Random();
        int numLetter = str.length() +1;
        int num1 = random1.nextInt(numLetter);
        if(num1 == 0){
            randomInsert = String.valueOf(ch)+ str;
            }else if(0 < num1 &&  num1 < (str.length()-1)){
                randomInsert = (str.substring(0, num1) + String.valueOf(ch) +str.substring(num1, str.length()) );
            }else{
                randomInsert = str + String.valueOf(ch);
          }   
      return (randomInsert);
    }   
    }

