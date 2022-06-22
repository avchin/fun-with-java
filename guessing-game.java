// This program generates a random number between 1 and a max
// value and prompts the user to guess the number until it is correct 
// and whether or not they want to play again, then prints 
// the statistics of the games.

import java.util.*;

public class GuessingGame {
   public static final int MAX_VALUE = 100;
   
   public static void main (String[] args) {
      Scanner console = new Scanner(System.in);
      Random rand = new Random();
      
      haiku();
      int numGuess = singleGame(rand, console);
      String response = anotherPrompt(console);
      int totalGuesses = numGuess;
      int best = numGuess;
      int games = 1;
      int numGuessAgain = 0;
      while (response.charAt(0) == 'y' || response.charAt(0) == 'Y') {
         System.out.println();
         numGuessAgain = singleGame(rand, console);
         totalGuesses += numGuessAgain;
         games++;
         if (numGuessAgain < numGuess) {
            best = numGuessAgain;
         }
         response = anotherPrompt(console);
      }
      statistics(games, totalGuesses, best);
   }
   
   // Prints a custom haiku.
   public static void haiku() {
      System.out.println("You're wanting a game?");
      System.out.println("You've come to the perfect place.");
      System.out.println("Guess until you've won!");
      System.out.println();
   }
   
   // Reads in a Random value and a Scanner and prompts the user for a guess.
   // Prints whether or not the Random value is higher or lower than the guess.
   // Once the user guesses the Random value, prints the number of guesses, and 
   // returns the number of guesses.
   public static int singleGame(Random rand, Scanner console) {
      int answer = rand.nextInt(MAX_VALUE) + 1;
      System.out.println("I'm thinking of a number between 1 and " + MAX_VALUE + "...");
      System.out.print("Your guess? ");
      int guess = console.nextInt();
      int numGuesses = 1;
      while (guess != answer) {
         if (answer < guess) {
            System.out.println("It's lower.");
         } else {
            System.out.println("It's higher.");
         }
         System.out.print("Your guess? ");
         int guessAgain = console.nextInt();
         guess = guessAgain;
         numGuesses += 1;
      }  
      if (numGuesses == 1) {
         System.out.println("You got it right in 1 guess!");
      } else {
         System.out.println("You got it right in " + numGuesses + " guesses!");
      }
      return numGuesses;
   }
   
   // Prompts the user if they would like to play another game and returns
   // the response as a string.
   public static String anotherPrompt(Scanner console) {
      System.out.print("Do you want to play again? ");
      String response = console.next();
      return response;
   }
   
   // Reads in the number of games, guesses, and best game and prints
   // the statistics for all games.
   public static void statistics(int games, int guesses, int best) {
      double guessPerGame = (double) guesses / games;
      System.out.println();
      System.out.println("Overall results:");
      System.out.println("Total games   = " + games);
      System.out.println("Total guesses = " + guesses);
      System.out.println("Guesses/game  = " + round2(guessPerGame));
      System.out.println("Best game     = " + best);
   }
   
   // Reads in a double and returns the number rounded to two decimal
   // points.
   public static double round2(double num) {
      return Math.round(num * 10.0) / 10.0;
   }
}
