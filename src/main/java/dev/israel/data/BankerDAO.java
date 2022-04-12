package dev.israel.data;

import dev.israel.entities.Banker;

public interface BankerDAO {

    // Create Account

    Banker createBanker(Banker banker);

    // Log In by UserName and Password
    Banker loginBanker(String username, String password);

    // Deposit Funds
    Banker updateFunds(Banker banker);

}
