package dev.israel.api;

import dev.israel.data.BankerDAOPostgresImpl;
import dev.israel.entities.Banker;
import dev.israel.services.BankerService;
import dev.israel.services.BankerServiceImpl;

import java.util.Scanner;

public class App {

    public static BankerService bankerService = new BankerServiceImpl(new BankerDAOPostgresImpl());

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Lamech's Banking app!");
        System.out.println("Choose what you want to do by entering the appropriate number. You can:");
        System.out.println("1. Login \n2. Create New Account");
        int accountChoice = scanner.nextInt();


        switch (accountChoice){
            case 1: {
                // Log In
                System.out.println("Please enter your username:");
                String username = scanner.next();
                System.out.println("Please enter your password");
                String password = scanner.next();

                Banker banker = bankerService.loginBanker(username, password);
                System.out.println("Welcome " + banker.getFirstName() + " " + banker.getLastName() + ".");
                System.out.println("What do you want to do today?");
                System.out.println("1. Check Balance \n2. Withdraw Funds \n3. Deposit Funds");
                int choice = scanner.nextInt();

                switch (choice){
                    // Check Balance
                    case 1:{
                        System.out.println("You have " + banker.getBalance() + " dollars in the bank.");
                    }break;

                    // Withdraw
                    case 2:{
                        System.out.println("How much do you want to take out?");
                        double withdraw = scanner.nextDouble();
                        bankerService.withdrawFunds(banker, withdraw);

                        System.out.println("You now have " + banker.getBalance() + " dollars in the bank.");
                    }break;

                    // Deposit
                    case 3:{
                        System.out.println("How much do you want to deposit?");
                        double deposit = scanner.nextDouble();
                        App.bankerService.depositFunds(banker, deposit);

                        System.out.println("You now have " + banker.getBalance() + " dollars in the bank.");
                    }
                }

            }break;
            case 2: {
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

                Banker banker = new Banker(0, username, password, firstName, lastName, 0);
                App.bankerService.newBanker(banker);

                System.out.println("Welcome " + firstName + " " + lastName + ".");

            } break;
        }
    }
}
