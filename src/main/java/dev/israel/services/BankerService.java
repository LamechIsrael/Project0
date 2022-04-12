package dev.israel.services;

import dev.israel.entities.Banker;

public interface BankerService {

    Banker newBanker(Banker banker);

    Banker loginBanker(String username, String password);

    Banker withdrawFunds(Banker banker, double amount);

    Banker depositFunds(Banker banker, double amount);


}
