package java_basics;

import java.util.Scanner;

public class Pattern extends Base {
    public static void displayMenu(){
        System.out.println("Welcome on the pattern platform");
        System.out.println("1. Triangle (Right Angled)\n2. Diamond( Hollow )\n3. Square with diagonal\n4. Pascal's Triangle");
        System.out.println("Enter your choice: ");
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        displayMenu();
        int choice = getValidChoice(scanner);
    }
}
