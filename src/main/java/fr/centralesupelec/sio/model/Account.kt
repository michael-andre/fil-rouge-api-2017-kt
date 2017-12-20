package fr.centralesupelec.sio.model

/**
 * An entity class to represent a user account.
 */
data class Account(
        var username: String,
        var passwordHash: String? = null
)
