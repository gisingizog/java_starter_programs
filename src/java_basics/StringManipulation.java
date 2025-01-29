package java_basics;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StringManipulation {
    public static void displayMenu(){
        System.out.println("Use the menu below to interact with your input");
        System.out.println("1. Reverse\n2. Change case\n3. Length\n4. Replace Characters\n5. Search for characters\n6. Match Pattern\n7. Split text\n8. Exit");
        System.out.println("Enter your preferred operation: ");
    }
    public static void displayWelcome(){
        System.out.println("Welcome on the String Manipulation Platform.");
        System.out.println("Please enter the your string: ");
    }
    public static int getValidChoice(Scanner scanner){
        int choice;
        while(true){
            try{
                System.out.println("Choose one operation from the list above: ");
                choice = scanner.nextInt();
                if(choice >=1 && choice <= 8){
                    return choice;
                }
                System.out.println("Error: Invalid choice. Please enter a value between 1 and 6");
            }catch(InputMismatchException e){
                System.out.println("Error: Invalid input type. Please provide a numerical value");
                scanner.nextLine();
            }
        }
    }
    public static String reverse(String input){
        StringBuilder reversed = new StringBuilder(new String());
        for(int i = input.length() - 1;i>=0;i--){
            reversed.append(input.charAt(i));
        }
        System.out.println(STR."Reversed: \{reversed}");
        return reversed.toString();
    }
    public static boolean isLowerCase(String input){
        return input.equals(input.toLowerCase());
    }
    public static String changeCase(String input){
        if(input.isBlank()){
            System.out.println("The string is blank");
            return input;
        }
        if(isLowerCase(input)){
            System.out.println("To uppercase....");
            return input.toUpperCase();
        }
        else{
            return input.toLowerCase();
        }
    }
    public static boolean charExists(char c, String word){
        int i = 0;
        while(i < word.length()){
            if(word.charAt(i) == c){
                return true;
            }
            i++;
        }
        return false;
    }
    public static String replaceChars(String input, Scanner scanner){
        char oldChar = '\0';
        char newChar = '\0';
        int step = 0;
        while(true){
            if(step == 0){
                System.out.println("Character to replace: ");
            }else{
                System.out.println("Character to replace with: ");
            }
            String charInput = scanner.nextLine();
            if(step == 0){
                oldChar = charInput.charAt(0);
                if(!charExists(oldChar, input)){
                    System.out.println(STR."Seems like character \{oldChar} does not exist in word \{input}");
                    continue;
                }
                step++;
            }else{
                newChar = charInput.charAt(0);
                break;
            }
        }
        return input.replace(oldChar, newChar);
    }
    public static ArrayList<Integer> searchChar(String input, char c){
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for(int i= 0; i< input.length(); i++){
            if(input.charAt(i) == c){
                positions.add(i+1);
            }
        }
        return positions;
    }
    public static boolean checkPattern(String input, int pattern){
        return switch (pattern) {
            case 1 -> Pattern.matches(".*\\d.*", input);
            case 2 -> Pattern.matches(".*[!@#$%^&*()_=].*", input);
            case 3 -> Pattern.matches(".*[a-zA-Z0-9].*", input);
            default -> {
                System.out.println("Error: Invalid choice. Please enter a valid choice");
                yield false;
            }
        };
    }
    public static void main(String[] args) {
        while(true){
            Scanner scanner = new Scanner(System.in);
            displayWelcome();
            String input = scanner.nextLine();
            displayMenu();

            int choice=getValidChoice(scanner);
            scanner.nextLine();

            if (choice == 8) {
                System.out.println("Exiting the program....Goodbye");
                break;
            }
            Object result;
            switch(choice){
                case 1:
                    System.out.println("OPERATION : REVERSE STRING");
                    result = reverse(input);
                    System.out.println(STR."RESULT: \{result}");
                    break;
                case 2:
                    System.out.println("OPERATION : CHANGE CASE");
                    result = changeCase(input);
                    System.out.println(STR."RESULT: \{result}");
                    break;
                case 3:
                    System.out.println("OPERATION : STRING LENGTH");
                    System.out.println(STR."String length: \{input.length()}");
                    break;
                case 4:
                    System.out.println("OPERATION : CHARACTER REPLACEMENT");
                    System.out.println(STR."Old String: \{input}");
                    result = replaceChars(input, scanner);
                    System.out.println(STR."New string: \{result}");
                    break;
                case 5:
                    System.out.println("OPERATION : CHAR SEARCH");
                    System.out.println("Enter the character to search for: ");
                    char character = scanner.nextLine().charAt(0);
                    System.out.println(STR."Available character '\{character}' position: \{searchChar(input, character)}");
                    break;
                case 6:
                    System.out.println("OPERATION : PATTERN MATCHING");
                    System.out.println("Here are the patterns to consider: ");
                    System.out.println("1. Contains numbers \n2. Contains special characters \n3. Both numbers and characters");
                    System.out.println("Enter your preferred pattern: ");
                    int pattern = scanner.nextInt();

                    System.out.println(STR."Result: \{checkPattern(input, pattern)}");
                    break;
                default:
                    break;
            }
        }
    }
}
