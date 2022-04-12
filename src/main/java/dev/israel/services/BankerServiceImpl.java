package dev.israel.services;

import dev.israel.data.BankerDAO;
import dev.israel.entities.Banker;
import dev.israel.utilities.BankerLogger;
import dev.israel.utilities.BankerLoggerLevel;

public class BankerServiceImpl implements BankerService{

    private BankerDAO bankerDAO;

    public BankerServiceImpl(BankerDAO bankerDAO){
        this.bankerDAO = bankerDAO;
    }


    @Override
    public Banker newBanker(Banker banker) {
        BankerLogger.bankLogInfo("New banker " + banker.getFirstName() + " " + banker.getLastName() +
                " added to Bank.", BankerLoggerLevel.INFO);
        return this.bankerDAO.createBanker(banker);


    }

    @Override
    public Banker loginBanker(String username, String password) {
        Banker banker = this.bankerDAO.loginBanker(username, password);
        BankerLogger.bankLogInfo(banker.getFirstName() + " " + banker.getLastName() + " of username " + banker.getUsername() +
                " has logged in.", BankerLoggerLevel.INFO);
        return banker;
    }

    @Override
    public Banker withdrawFunds(Banker banker, double amount) {
        if(amount>0){

            // Withdrawl amount cannot be greater than the amount in the bank.
            if(banker.getBalance()>=amount) {
                banker.setBalance(banker.getBalance() - amount);
                this.bankerDAO.updateFunds(banker);
            }else if(banker.getBalance()<amount){
                System.out.println("Not enough funds.");
                BankerLogger.bankLogInfo(banker.getFirstName() + " " + banker.getLastName() + " of username " + banker.getUsername() +
                        " tried to withdraw more funds than availale.", BankerLoggerLevel.ERROR);
            }
        }else System.out.println("Not a valid amount.");
        BankerLogger.bankLogInfo(banker.getFirstName() + " " + banker.getLastName() + " of username " + banker.getUsername() +
                " has has withdrawn $" + amount + ".", BankerLoggerLevel.INFO);

        return banker;
    }

    @Override
    public Banker depositFunds(Banker banker, double amount) {
        if(amount>0){
            banker.setBalance(banker.getBalance() + amount);
            this.bankerDAO.updateFunds(banker);
        }else System.out.println("Not a valid amount.");

        BankerLogger.bankLogInfo(banker.getFirstName() + " " + banker.getLastName() + " of username " + banker.getUsername() +
                " has has deposited $" + amount + ".", BankerLoggerLevel.INFO);


        return banker;
    }

}
