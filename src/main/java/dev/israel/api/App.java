package dev.israel.api;

import dev.israel.data.BankerDAOPostgresImpl;
import dev.israel.entities.Banker;
import dev.israel.services.BankerService;
import dev.israel.services.BankerServiceImpl;

import java.util.Scanner;

public class App {

    public static Scanner scanner = new Scanner(System.in);
    public static BankerService bankerService = new BankerServiceImpl(new BankerDAOPostgresImpl());
    static Banker banker = null;

    public static void main(String[] args) {
        mainMenu();

    }

    public static void mainMenu(){
        System.out.println("Welcome to Lamech's Banking app!");
        System.out.println("Choose what you want to do by entering the appropriate number. You can:");
        System.out.println("1. Login \n2. Create New Account \n3. Exit");
        int accountChoice = scanner.nextInt();


        switch (accountChoice){
            case 1: {
                loginCreds();
            }break;
            case 2: {
                createBankerAccount();
            } break;
            case 3:{
                System.out.println("Goodbye!");
            }
        }
    }


    // Create New Bank Account
    public static void createBankerAccount(){
        // Create Account
        System.out.println("Enter your credentials:");
        System.out.println("What will be your username?");
        String username = scanner.next();
        System.out.println("What will be your password?");
        String password = scanner.next();
        System.out.println("What is your first name?");
        String firstName= scanner.next();
        System.out.println("Your last name?");
        String lastName = scanner.next();

        banker = new Banker(0, username, password, firstName, lastName, 0);
        App.bankerService.newBanker(banker);
        System.out.println("Welcome " + banker.getFirstName() + " " + banker.getLastName() + ".");

        loggedInChoices();
    }

    //Log In to Bank Account
    public static void loginCreds(){
        System.out.println("Please enter your username:");
        String username = scanner.next();
        System.out.println("Please enter your password:");
        String password = scanner.next();


        banker = bankerService.loginBanker(username, password);

        if(banker == null){
            mainMenu();
        }
        System.out.println("Welcome " + banker.getFirstName() + " " + banker.getLastName() + ".");

        loggedInChoices();
    }

    // Withdraw, Deposit, or Check Balance
    public static void loggedInChoices(){
        // Welcome Screen
        System.out.println("\nWhat do you want to do today?");
        System.out.println("1. Check Balance \n2. Withdraw Funds \n3. Deposit Funds \n4. Exit");
        int choice = scanner.nextInt();

        switch (choice){
            // Check Balance
            case 1:{
                System.out.printf("You have $%.2f in the bank.\n", banker.getBalance());
                loggedInChoices();
            }break;

            // Withdraw
            case 2:{
                System.out.println("How much do you want to take out?");
                double withdraw = scanner.nextDouble();
                bankerService.withdrawFunds(banker, withdraw);

                System.out.printf("You have $%.2f in the bank.\n", banker.getBalance());
                loggedInChoices();
            }break;

            // Deposit
            case 3:{
                System.out.println("How much do you want to deposit?");
                double deposit = scanner.nextDouble();
                App.bankerService.depositFunds(banker, deposit);

                System.out.printf("You have $%.2f in the bank.\n", banker.getBalance());
                loggedInChoices();
            }break;

            // Exit
            case 4:{
                System.out.println("Goodbye.");
            }
        }
    }
}
