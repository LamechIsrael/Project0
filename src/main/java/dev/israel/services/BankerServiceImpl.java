package dev.israel.services;

import dev.israel.data.BankerDAO;
import dev.israel.entities.Banker;

public class BankerServiceImpl implements BankerService{

    private BankerDAO bankerDAO;

    public BankerServiceImpl(BankerDAO bankerDAO){
        this.bankerDAO = bankerDAO;
    }


    @Override
    public Banker newBanker(Banker banker) {
        return this.bankerDAO.createBanker(banker);
    }

    @Override
    public Banker loginBanker(String username, String password) {
        Banker banker = this.bankerDAO.loginBanker(username, password);
        return banker;
    }

    @Override
    public Banker withdrawFunds(Banker banker, double amount) {
        if(banker.getBalance()>=amount) {
            banker.setBalance(banker.getBalance() - amount);
            this.bankerDAO.updateFunds(banker);
        }else System.out.println("Not enough funds.");
        return banker;
    }

    @Override
    public Banker depositFunds(Banker banker, double amount) {
        banker.setBalance(banker.getBalance() + amount);
        this.bankerDAO.updateFunds(banker);

        return banker;
    }

}
