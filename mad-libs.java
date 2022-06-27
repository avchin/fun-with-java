// This program reads a file with placeholders and prompts the user for
// words to replace the placeholders to generate a unique Mad Lib and print
// it to a new file.

import java.util.*;
import java.io.*;

public class MadLibs {
   public static void main (String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      
      intro();
      String choice = menu(console);
      while (!choice.equalsIgnoreCase("q")) {   
         if (choice.equalsIgnoreCase("c")) {
            String infile = promptInput(console);
            String outfile = promptOutput(console);
            create(console, infile, outfile);
         } else if (choice.equalsIgnoreCase("v")) {
            String infile = promptInput(console);
            view(console, infile);
         }
         System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? "); 
         choice = console.nextLine();
      }
      System.out.println();   
   }
   
   // Prints the intro statement.
   public static void intro() {
      System.out.println("Welcome to the game of Mad Libs.");
      System.out.println("I will ask you to provide various words");
      System.out.println("and phrases to fill in a story.");
      System.out.println("The result will be written to an output file.");
      System.out.println();
   }
   
   // Reads in a scanner and prompts the user for a response on the menu.
   // Returns the response as a string.
   public static String menu(Scanner console) throws FileNotFoundException {
      System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
      String choice = console.nextLine();
      return choice;
   }   
   
   // Prompts the user for an input file. 
   // Returns the input file name as a string.
   public static String promptInput(Scanner console) throws FileNotFoundException {
      System.out.print("Input file name: ");
      String inputFileName = console.nextLine(); 
      File inputFile = new File(inputFileName);
      while (!inputFile.exists()) {
         System.out.print("File not found. Try again: ");
         inputFileName = console.nextLine();
         inputFile = new File(inputFileName);
      }
      Scanner input = new Scanner(inputFile);
      return inputFileName;
   }
   
   // Prompts the user for an output file.
   // Returns the output file name as a string.
   public static String promptOutput(Scanner console) throws FileNotFoundException {
      System.out.print("Output file name: ");
      String outputFile = console.nextLine();
      System.out.println();
      return outputFile;
   }
   
   // Reads in a scanner, the input file string, and the output file string. 
   // Reads the input file for placeholders and prompts the user for words 
   // to replace the placeholders and writes the result to an output file.
   public static void create(Scanner console, String inFile, String outFile) throws 
         FileNotFoundException {
      PrintStream output = new PrintStream(new File(outFile)); 
      Scanner input = new Scanner(new File(inFile));
      String vowels = "aeiouAEIOU";
      while (input.hasNextLine()) {
         String line = input.nextLine();
         Scanner lineScan = new Scanner(line);
         String oneLine = "";
         while (lineScan.hasNext()) {
            String word = lineScan.next();
            if (word.charAt(0) == '<' && word.charAt(word.length() - 1) == '>') {
               word = word.replace("-", " ");
               word = word.substring(1, word.length() - 1);
               if (vowels.indexOf(word.charAt(0)) != -1) {
                  System.out.print("Please type an " + word + ": ");
               } else {
                  System.out.print("Please type a " + word + ": ");
               }
               word = console.nextLine();
            }
            oneLine += (word + " "); 
         }
         output.println(oneLine);
      }
      System.out.println("Your mad-lib has been created!");
      System.out.println();
   }
   
   // Reads in a scanner and the input file string.
   // Prints the contents of the input file to the console.
   public static void view(Scanner console, String inFile) throws 
         FileNotFoundException {
      System.out.println();
      Scanner input = new Scanner(new File(inFile));
      while (input.hasNextLine()) {
         String line = input.nextLine();
         System.out.println(line);
      }
      System.out.println();
   }
}
