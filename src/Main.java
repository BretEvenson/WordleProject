import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final String[] WORDS = {
            "APPLE", "BEGIN", "LISTS" , "WORDS" , "DELTA", "CHOSE", "HELLO"};
    static int attempts = 0;

    public static void main(String[] args) {
        int i = ThreadLocalRandom.current().nextInt(0, WORDS.length);
        String randomWord = WORDS[i];
        List<String> word_results;
        boolean gameWon = false;

        do {
            try{
            System.out.print("\nType your guess here: ");
            String userInput = getInput();
            System.out.println(userInput);
            word_results = compareWords(userInput, randomWord);

            for (i = 0; i < word_results.toArray().length; i++)
                System.out.print(word_results.get(i));
            gameWon = winOrLose(word_results);
            attempts ++;}

            catch (Exception e){
                System.out.println("Invalid input");}

        } while (!gameWon && attempts < 6);

        if (attempts > 6)
            System.out.println("\nYou lost! The word was: " + randomWord);
        else
            System.out.println("\nYou won! The word was: " + randomWord);

    }
     public static String getInput() throws Exception{
         Scanner scan = new Scanner(System.in);
         String input = scan.nextLine();

         if (input.length() != 5)
             throw new Exception();
         else
            return input.toUpperCase();
        }

     public static List<String> compareWords(String user_input, String random_word){
        List<String> word_results = new ArrayList<>();
        List<String> green_letters = new ArrayList<>();
        String[] random_word_broken = random_word.split("");
        String[] user_word_broken = user_input.split("");

        for (int i = 0; i < user_input.length(); i++)

            if(user_word_broken[i].equals(random_word_broken[i])){
                word_results.add("g");
                green_letters.add(user_word_broken[i]);}
            else
                word_results.add("");

         for (int i = 0; i < user_input.length(); i++)

            if (word_results.get(i).equals("g"))
                continue;
            else if (random_word.contains(user_word_broken[i])  && !green_letters.contains(user_word_broken[i]))
                    word_results.set(i, ("y"));
            else
                word_results.set(i, ("r"));

        return word_results;
     }
     public static boolean winOrLose(List<String> word_results){
            if (word_results.contains("r") || word_results.contains("y") || word_results.contains(""))
                return false;
            else
                return true;
            }
     //public static boolean inWordList(String guess){}

     }