package java_basics;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SimpleCalculator extends Base{
    public static void displayMenu() {
        System.out.println("Welcome on Calc App");
        System.out.println("1. Add( + ) \n2. Subtract( - ) \n3. Multiply( * ) \n4. Divide( / ) \n5. Modulus( % )\n6. Exit");
    }

    public static float add(float a, float b) {
        return a + b;
    }

    public static float subtract(float a, float b) {
        return a - b;
    }

    public static float multiply(float a, float b) {
        return a * b;
    }

    public static float divide(float a, float b) {
        if (b == 0) {
            System.out.println("Error: Division by zero not allowed");
            return Float.NaN;
        }
        return a / b;
    }

    public static float modulus(float a, float b) {
        if (b == 0) {
            System.out.println("Error: Modulus by zero not allowed");
            return Float.NaN;
        }
        return a % b;
    }

    public static float[] readOperands(Scanner scanner) {
        float[] operands = new float[2];
        for (int i = 0; i < operands.length; i++) {
            while (true) {
                try {
                    System.out.println("Enter operand (" + (i + 1) + "): ");
                    operands[i] = scanner.nextFloat();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Invalid input. Please enter a valid number");
                    scanner.nextLine();
                }
            }
        }
        return operands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = getValidChoice(scanner);
            if (choice == 6) {
                System.out.println("Exiting the program....");
                break;
            }

            float[] operands = readOperands(scanner);
            float num1 = operands[0];
            float num2 = operands[1];
            float result;

            switch (choice) {
                case 1:
                    result = add(num1, num2);
                    System.out.println("The sum of " + num1 + " and " + num2 + " = " + result);
                    break;
                case 2:
                    result = subtract(num1, num2);
                    System.out.println("The difference between " + num1 + " and " + num2 + " = " + result);
                    break;
                case 3:
                    result = multiply(num1, num2);
                    System.out.println("The product of " + num1 + " and " + num2 + " = " + result);
                    break;
                case 4:
                    result = divide(num1, num2);
                    if (!Float.isNaN(result)) {
                        System.out.println("The quotient of " + num1 + " and " + num2 + " = " + result);
                    }
                    break;
                case 5:
                    result = modulus(num1, num2);
                    if (!Float.isNaN(result)) {
                        System.out.println("The modulus of " + num1 + " and " + num2 + " = " + result);
                    }
                    break;
                default:
                    System.out.println("Invalid input. Try Again");
                    break;
            }
        }
        scanner.close();
    }
}
