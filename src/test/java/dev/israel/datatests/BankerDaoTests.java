package dev.israel.datatests;

import dev.israel.data.BankerDAO;
import dev.israel.data.BankerDAOPostgresImpl;
import dev.israel.entities.Banker;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankerDaoTests {

    static BankerDAO bankerDAO = new BankerDAOPostgresImpl();
    static Banker testBanker = null;

    @Test
    @Order(1)
    void createUserTest(){
        Banker jonathan = new Banker(50,"joreese","saveReese123", "Joshua", "Reese",2000);
        Banker savedBanker = bankerDAO.createBanker(jonathan);
        BankerDaoTests.testBanker = savedBanker;
        Assertions.assertNotEquals(0,savedBanker.getId());
    }

    @Test
    @Order(2)
    void login_banker() {
        Banker retrievedBanker = bankerDAO.loginBanker(testBanker.getUsername(), testBanker.getPassword());
        Assertions.assertEquals("joreese", retrievedBanker.getUsername());
    }

    @Test
    @Order(3)
    void update_funds(){
        BankerDaoTests.testBanker.setBalance(testBanker.getBalance()+200);
        bankerDAO.updateFunds(testBanker);

        Assertions.assertEquals(2200, testBanker.getBalance());


    }

    @Test
    @Order(4)
    void check_balance(){
        System.out.println("You have " + testBanker.getBalance() + " in the bank.");
    }
}
