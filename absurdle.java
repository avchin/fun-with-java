// The AbsurdleManager class narrows down a list of words, all with 
// the same length, based on guesses by the client, until there is
// a single word left.
import java.util.*;

public class AbsurdleManager {
    private Set<String> dict;
    private int len;

    // Constructs a dictionary of words that all have the same given length.
    // Throws an IllegalArgumentException if the given length is less than one.
    public AbsurdleManager(Collection<String> dictionary, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("No words of such length.");
        }
        len = length;
        dict = new TreeSet<String>();
        for (String word : dictionary) {
            if (word.length() == length) {
                dict.add(word);
            }
        }
    }

    // Returns the stored dictionary of words.
    public Set<String> words() {
        return dict;
    }

    // The comment for this method is provided. Do not modify this comment:
    // Params:
    //  String word -- the secret word trying to be guessed. Assumes word is made up of only
    //                 lower case letters and is the same length as guess.
    //  String guess -- the guess for the word. Assumes guess is made up of only
    //                  lower case letters and is the same length as word.
    // Exceptions:
    //   none
    // Returns:
    //   returns a string, made up of gray, yellow, or green squares, representing a
    //   standard wordle clue for the provided guess made against the provided secret word.
    public static String patternFor(String word, String guess) {
        int length = word.length();
        String current = word;
        String guessed = guess;
        String[] pattern = new String[length];
        TreeMap<Character, Integer> counts = new TreeMap<Character, Integer>();
        for (int i = 0; i < length; i++) {
            char letter = word.charAt(i);
            if (counts.keySet().contains(letter)) {
                counts.put(letter, counts.get(letter) + 1);
            } else {
                counts.put(letter, 1);
            }
            if (letter == guess.charAt(i)) {
                counts.put(letter, counts.get(letter) - 1);
                pattern[i] = "🟩";
                if (i == length - 1) {
                    current = current.substring(0, i) + "0";
                    guessed = guessed.substring(0, i) + "-";
                } else {
                    current = current.substring(0, i) + "0" + 
                        current.substring(i + 1);
                    guessed = guessed.substring(0, i) + "-" + 
                        guessed.substring(i + 1);
                }
            }
        }
        for (int i = 0; i < length; i++) {
            char guessChar = guessed.charAt(i);
            if (counts.keySet().contains(guessChar) && 
                    counts.get(guessChar) > 0) {
                counts.put(guessChar, counts.get(guessChar) - 1);
                pattern[i] = "🟨";
            }
        }
        String finalPattern = "";
        for (int i = 0; i < length; i++) {
            if (pattern[i] == null) {
                pattern[i] = "⬜";
            }
            finalPattern += pattern[i];
        }
        return finalPattern;
    }

    // Takes in a String as a parameter that represents the guess of the client. 
    // Assumes the guess is made up of only lower case letters.
    // Throws an IllegalStateException when the dictionary is empty.
    // Throws an IllegalArgumentException when the guess is of a different length
    // than given in the constructor. 
    // Returns the wordle clue in the same format as in the patternFor() method that
    // is associated with the largest number of possible words and updates this 
    // dict with the possible words.
    public String record(String guess) {
        if (dict.isEmpty()) {
            throw new IllegalStateException("No words in dictionary.");
        } else if (guess.length() != len) {
            throw new IllegalArgumentException("Incorrect word length.");            
        }
        Map<String, Set<String>> patterns = new TreeMap<String, Set<String>>();
        for (String word: dict) {
            String pattern = patternFor(word, guess);
            if (patterns.containsKey(pattern)) {
                Set<String> set = patterns.get(pattern);
                set.add(word);
                patterns.put(pattern, set);
            } else {
                Set<String> newSet = new TreeSet<String>();
                newSet.add(word);
                patterns.put(pattern, newSet);
            }
        }
        int max = 0;
        String finalPattern = "";
        for (String pattern: patterns.keySet()) {
            Set<String> words = patterns.get(pattern);
            if (words.size() > max) {
                max = words.size();
                finalPattern = pattern;
                dict = words;
            }
        }
        return finalPattern;
    }
}
