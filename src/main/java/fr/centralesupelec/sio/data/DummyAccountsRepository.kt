package fr.centralesupelec.sio.data

import fr.centralesupelec.sio.model.Account

/**
 * A concrete [AccountsRepository] backed by an in-memory list of static [Account] entities.
 */
internal class DummyAccountsRepository : AccountsRepository {

    private val accounts: List<Account> = listOf(
            Account(username = "admin@ecp.sio.fr", passwordHash = "password")
    )

    override fun getAccount(username: String): Account? =
        accounts.firstOrNull { it.username.equals(username, ignoreCase = true) }

}
