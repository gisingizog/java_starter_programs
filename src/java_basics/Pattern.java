package java_basics;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Pattern extends Base {

    private static final Scanner scanner = new Scanner(System.in);

    private static void displayMenu() {
        System.out.println("Welcome on the pattern platform");
        System.out.println("1. Triangle (Right Angled)\n2. Diamond (Hollow)\n3. Square with Diagonal\n4. Pascal's Triangle\n5. Exit");
    }

    private static int getNumberOfRows() {
        while (true) {
            try {
                System.out.println("Enter the number of rows: ");
                int n = scanner.nextInt();
                if (n > 1) {
                    return n;
                }
                System.out.println("Error: Input too small. Please enter an integer greater than 1.");
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. The input must be an integer.");
                scanner.nextLine();
            }
        }
    }

    private static void rightTriangle() {
        int n = getNumberOfRows();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    private static void upPyramid(int n) {
        for (int i = 1; i <= n; i++) {
            // Print leading spaces for alignment
            for (int j = i; j < n; j++) {
                System.out.print(" ");
            }
            // Print hollow stars for the top pyramid
            makeHollowRow(i);
        }
    }

    private static void invertedPyramid(int n) {
        for (int i = n - 1; i >= 1; i--) {
            // Print leading spaces for alignment
            for (int j = n; j > i; j--) {
                System.out.print(" ");
            }
            // Print hollow stars for the bottom pyramid
            makeHollowRow(i);
        }
    }

    private static void hollowDiamond() {
        int n = getNumberOfRows();
        upPyramid(n);
        invertedPyramid(n);
    }

    private static void makeHollowRow(int row) {
        for (int k = 1; k <= (2 * row - 1); k++) {
            if (k == 1 || k == (2 * row - 1)) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    private static void squareDiagonal(){
        int n = getNumberOfRows();
        for(int i = 0; i<n; i++){
            for(int j = 0; j < n; j++){
                if(i ==0 || i==n-1 || j==0 || j == n-1 || i==j || i+j==n-1){
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    private static void pascalTriangle() {
        int rows = getNumberOfRows();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows - i; j++) {
                System.out.print(" ");
            }
            int number = 1;
            for (int k = 0; k <= i; k++) {
                // Print the number followed by a space
                System.out.print(STR."\{number} ");
                number = number * (i - k) / (k + 1); // Update number based on binomial formula
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getValidChoice(scanner);
            if (choice == 5) {
                System.out.println("Exiting the program... Goodbye!");
                break;
            }
            switch (choice) {
                case 1:
                    System.out.println("Right-angled triangle");
                    rightTriangle();
                    break;
                case 2:
                    System.out.println("Hollow Diamond");
                    hollowDiamond();
                    break;
                case 3:
                    System.out.println("Square with diagonal");
                    squareDiagonal();
                    break;
                case 4:
                    System.out.println("Pascal's Triangle");
                    pascalTriangle();
                    break;
                case 6:
                    upPyramid(5);
                    break; 
                default:
                    System.out.println("Invalid choice. Try again later.");
                    break;
            }
        }
        scanner.close(); // Close scanner only once at the end
    }
}
