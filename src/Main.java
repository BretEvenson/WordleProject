import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Main {

    public static final List<String> WORDS = List.of(
            "APPLE", "BEGIN", "LISTS", "WORDS", "DELTA", "CHOSE", "HELLO", "CANOE","ALPHA","GAMMA", "BETTA"
    );
    static int attempts = 0;

    public static void main(String[] args) {
        int i = ThreadLocalRandom.current().nextInt(0, WORDS.size());
        String randomWord = WORDS.get(i);
        List<String> word_results;
        boolean gameWon = false;

        do {
            try{
                System.out.print("\nType your guess here: ");
                String userInput = getInput();
                System.out.println(userInput);
                //inWordList(userInput, WORDS);
                word_results = compareWords(userInput, randomWord);
                for (i = 0; i < word_results.toArray().length; i++)
                    System.out.print(word_results.get(i));
                gameWon = winOrLose(word_results);
                attempts ++;}

            catch (Exception e){
                System.out.println(e.getMessage());}

        } while (!gameWon && attempts < 6);

        if (gameWon)
            System.out.println("\nYou won! The word was: " + randomWord);
        else
            System.out.println("\nYou lost! The word was: " + randomWord);


    }
    public static String getInput() throws Exception{
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        if (input.length() != 5)
            throw new Exception ("Word must be 5 characters long");
        else
            return input.toUpperCase();
    }

    public static List<String> compareWords(String user_input, String random_word) {
        List<String> word_results = new ArrayList<>(Arrays.asList("", "", "", "", ""));
        List<String> random_word_broken = new ArrayList<>(List.of(random_word.split("")));
        List<String> user_word_broken = new ArrayList<>(List.of(user_input.split("")));

        for (int i = 0; i < user_word_broken.size(); i++)
            if (user_word_broken.get(i).equals(random_word_broken.get(i))) {
                word_results.set(i, "g");
                random_word_broken.set(i, null);
                user_word_broken.set(i, null);
            }

        for (int i = 0; i < user_word_broken.size(); i++) {
            if ("g".equals(word_results.get(i)))
                continue;

            String current_letter = user_word_broken.get(i);
            if (current_letter == null)
                continue;
            if (random_word_broken.contains(current_letter)) {
                word_results.set(i, "y");
                int j = random_word_broken.indexOf(current_letter);
                if (j >= 0) random_word_broken.set(j, null);
                user_word_broken.set(i, null);
            } else {
                word_results.set(i, "r");
            }
        }
        return word_results;
    }
    public static boolean winOrLose(List<String> word_results){
        if (word_results.contains("r") || word_results.contains("y") || word_results.contains(""))
            return false;
        else
            return true;
    }

    public static void inWordList(String guess, List<String> WORDS) throws Exception{
        if (WORDS.contains(guess))
            return;
        else {
            throw new Exception("Word must be a valid word in the list");
        }
    }
}