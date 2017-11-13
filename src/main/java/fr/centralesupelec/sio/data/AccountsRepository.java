package fr.centralesupelec.sio.data;

import fr.centralesupelec.sio.model.Account;
import fr.centralesupelec.sio.model.Movie;

import java.util.List;

public abstract class AccountsRepository {

    private static AccountsRepository sRepository;

    public static AccountsRepository getInstance() {
        if (sRepository == null) {
            sRepository = new DummyAccountsRepository();
        }
        return sRepository;
    }

    protected AccountsRepository() { }

    public abstract Account getAccount(String username);

}
