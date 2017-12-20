package fr.centralesupelec.sio.data

import fr.centralesupelec.sio.model.Account

/**
 * A data repository to expose account-related entities.
 */
interface AccountsRepository {

    companion object {
        val sharedInstance: AccountsRepository by lazy { DummyAccountsRepository() }
    }

    /**
     * Find an account with a given name.
     * The matching is case-insensitive.
     * @param username The name of the user.
     * @return The [Account] entity, or null if it does not exist.
     */
    fun getAccount(username: String): Account?

    // TODO: Add other movie-related methods here

}
