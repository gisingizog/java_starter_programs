package java_basics;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Base {
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

}
