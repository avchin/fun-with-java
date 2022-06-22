// This program encrypts a key word and then a specified number
// of words using a Caeser cipher. 

import java.util.*;

public class EncryptionMachine {
   public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
   public static final int SHIFT = 3;
   
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      
      intro();
      secretMessage(console);
      message(console);
   
   }
   
   // Prints the intro statement.   
   public static void intro() {
      System.out.println("Welcome to the CSE142 Encryption Machine!");
      System.out.println("The program lets you encrypt a message");
      System.out.println("with a key so your recipient can decrypt.");
      System.out.println();
   }
   
   // Reads Scanner input and prints prompts that ask for the key and number 
   // of words in the message.  
   public static void secretMessage(Scanner console) {
      System.out.println("Encrypted messages use a shared keyword to decrypt.");
      System.out.print("  Enter key: ");
      String key = console.next();
      System.out.print("    \"" + key + "\" " + "has been encrypted to: ");
      encryption(key);
      System.out.println();
   }
    
   // Reads string parameter input "type", encrypts string, and prints 
   // the newly encrypted string.
   public static void encryption(String type) {
      for (int i = 0; i <= type.length() - 1; i++) {
         int letterIndex = (ALPHABET.indexOf(type.charAt(i)) + SHIFT) % ALPHABET.length(); 
         char letter = ALPHABET.charAt(letterIndex);
         System.out.print(letter);    
      } 
   }

   // Reads Scanner input, prints prompt for number of words, 
   // next words in message, and ending statement.   
   public static void message(Scanner console) {
      System.out.println();
      System.out.print("How many words are in your message? ");
      int numberOfWords = console.nextInt();
      for (int i = 1; i <= numberOfWords; i++) {
         wordsPrompt(console);
      }
      System.out.println();
      System.out.println("Message fully encrypted. Happy secret messaging!");
   }
   
   // Reads Scanner input, prints prompt for next word and encrypted word.   
   public static void wordsPrompt(Scanner console) {
      System.out.print("  Next word: ");
      String word = console.next();
      System.out.print("    \"" + word + "\" " + "has been encrypted to: ");
      encryption(word);
      System.out.println();
   }
   
}
