package fr.centralesupelec.sio.endpoints

import fr.centralesupelec.sio.data.AccountsRepository
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.TextCodec

/**
 * A (simplified) class to handle authentication-related stuff in the API.
 * The manager is in charge of generating access token when a user logs in, an to verify them afterward.
 * Tokens are JWTs (JSON Web Tokens): they are not encrypted (simply base-64 encoded), but their signature can be verified to check if they have be tempered.
 */
// Defines an object available without instance creation.
// This is roughly equivalent to "static" in Java, which does not exist in Kotlin.
object AuthManager {

    private val SIGNATURE = TextCodec.BASE64.decode("b5dfdd86fdc777b34b78a7fe976aef9b54767400e73bae310b74ab2884a109b6")
    val ISSUER = "fr.centralesupelec.movies"

    /**
     * Generate a new access token for a user.
     * @param username The name of the user.
     * @return A newly created access token.
     */
    // TODO: Switch to an ID? Can users change their username?
    fun generateAccessToken(username: String): String =
            Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGNATURE)
                .setIssuer(ISSUER)
                .setSubject(username)
                .compact()

    fun checkAccessToken(accessToken: String): Boolean =
        try {
            val claims = Jwts.parser()
                    .setSigningKey(SIGNATURE)
                    .requireIssuer(ISSUER)
                    .parseClaimsJws(accessToken)
                    .body
            val username = claims.subject
            // Check that the user still exists
            // TODO: Maybe add a better mechanism?
            AccountsRepository.sharedInstance.getAccount(username) != null
        } catch (ex: JwtException) {
            false
        }

}
