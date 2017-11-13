package fr.centralesupelec.sio.endpoints;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;

/*
    <dependency>
      <groupId>io.jsonwebtoken</groupId>
      <artifactId>jjwt</artifactId>
      <version>0.9.0</version>
    </dependency>
 */
public class AuthManager {

    private static final byte[] SIGNATURE = TextCodec.BASE64.decode("b5dfdd86fdc777b34b78a7fe976aef9b54767400e73bae310b74ab2884a109b6");

    public static String generateAccessToken(String username) {
        JwtBuilder b = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGNATURE)
                .setIssuer("fr.centralesupelec.movies")
                .setSubject(username);
        return b.compact();
    }

    public static boolean checkAccessToken(String accessToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SIGNATURE)
                    .parseClaimsJws(accessToken)
                    .getBody();
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

}
