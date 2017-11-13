package fr.centralesupelec.sio.data;

import fr.centralesupelec.sio.model.Account;
import fr.centralesupelec.sio.model.Movie;

import java.util.Arrays;

public class DummyAccountsRepository extends AccountsRepository {

    @Override
    public Account getAccount(String username) {
        Account a1 = new Account();
        a1.setUsername("admin@ecp.sio.fr");
        a1.setPasswordHash("toto");
        return a1.getUsername().equals(username) ? a1 : null;
    }

}
