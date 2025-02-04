package java_basics.user_management;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserService {
    private static ArrayList<User> users = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    public static LocalDate getValidDob(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while(true){
            System.out.println("Date of birth(YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            try{
                return LocalDate.parse(dateInput, formatter);
            }catch(DateTimeParseException e){
                System.out.println("Invalid input. Use this format YYYY-MM-DD");
            }
        }
    }
    public static int getUpdatableProperty(){
        while(true){
            System.out.println("1. First Name\n2. Last Name\n3. Email\n4. Password\n5. PhoneNumber\n6. Date of birth");
            System.out.println("Enter the number corresponding to the item to update");
            try{
                return scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Enter an integer value");
            }
        }
    }
    public static User getUserByEmail(String email){
        for(User user : users){
            if(email.equals(user.getEmail())){
                return user;
            }
        }
        System.out.println(STR."User with email: \{email} doesn't exist");
        return null;
    }
    public static void saveUserToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))){
            for(User user : users){
                writer.write(user.toString());
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println(STR."I/O Exception thrown: \{e.getMessage()}");
        }
    }
    public static void createUser(){
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("Phone number: ");
        String phoneNumber = scanner.nextLine();
        LocalDate dob = getValidDob();
        users.add(new User(firstName, lastName, email, password, phoneNumber, dob));
        saveUserToFile();
        System.out.println("Registration successful");
    }
    public static void login(){
        System.out.println("Email address: ");
        String email = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        for(User user : users){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                System.out.println("Logged in successfully");
                return;
            }
        }
        System.out.println("Incorrect email address or password");
    }
    public static void getUsers(){
        if(users.isEmpty()){
            System.out.println("No registered users available");
        }
        else{
            for(User user : users){
                System.out.println(STR."\{user.toString()}");
            }
        }

    }
    public static void updateUser(){
        User user;
        do {
            System.out.println("Your email: ");
            String email = scanner.nextLine();
            user = getUserByEmail(email);
        } while (user == null);
        int choice = getUpdatableProperty();
        switch(choice){
            case 1:
                System.out.println("First Name: ");
                String firstName = scanner.nextLine();
                user.setFirstName(firstName);
                break;
            case 2:
                System.out.println("Last Name: ");
                String lastName = scanner.nextLine();
                user.setLastName(lastName);
                break;
            case 3:
                System.out.println("Email Address: ");
                String email = scanner.nextLine();
                user.setEmail(email);
                break;
            case 4:
                System.out.println("Password : ");
                String password = scanner.nextLine();
                user.setPassword(password);
                break;
            case 5:
                System.out.println("Phone Number: ");
                String phoneNumber = scanner.nextLine();
                user.setPhoneNumber(phoneNumber);
                break;
            case 6:
                LocalDate dob = getValidDob();
                user.setDob(dob);
                break;
            default:
                System.out.println("Invalid input. Enter an input from the list provided");
                break;
        }
    }
    public static void deleteUser(){
        System.out.println("DELETE USER");
        User u;
        do {
            System.out.println("Your email: ");
            String email = scanner.nextLine();
            u = getUserByEmail(email);
        } while (u == null);
        for(User user : users){
            if(user == u){
                users.remove(user);
            }
        }
    }
    public static void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length < 6) {  // Ensure that all fields are present
                    System.out.println(STR."Skipping malformed line: \{line}");
                    continue;
                }
                String firstName = userData[0];
                String lastName = userData[1];
                String email = userData[2];
                String password = userData[3];
                String phoneNumber = userData[4];

                try {
                    LocalDate dob = LocalDate.parse(userData[5]);
                    users.add(new User(firstName, lastName, email, password, phoneNumber, dob));
                } catch (Exception e) {
                    System.out.println(STR."Skipping line due to invalid date format: \{line}");
                }
            }
        } catch (IOException e) {
            System.out.println("I/O Exception thrown: " + e.getMessage());
        }
    }

    public static void start(){
        loadDataFromFile();
        System.out.println("\nWELCOME ON THE USER MANAGEMENT APPLICATION");
        while(true){
            System.out.println("1. Register a new user\n2. Get all users\n3. Update a user\n4. Delete a user\n5. Login\n6. Exit");
            System.out.println("Enter your preferred choice: ");
            int choice = scanner.nextInt();
            if(choice == 6){
                System.out.println("Closing the application....GoodBye");
                break;
            }
            switch (choice){
                case 1:
                    System.out.println("REGISTER A USER");
                    createUser();
                    break;
                case 2:
                    System.out.println("GET ALL USERS");
                    getUsers();
                    break;
                case 3:
                    System.out.println("UPDATE A USER");
                    updateUser();
                    break;
                case 4:
                    System.out.println("DELETE A USER");
                    deleteUser();
                    break;
                case 5:
                    System.out.println("LOGIN");
                    login();
                    break;
                default:
                    System.out.println("Invalid Input. Choose a number from the menu");
            }
        }

    }
}
