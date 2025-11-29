package fr.orleans.m1s1miage.group4.backend.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final RsaKeyProvider keyProvider;
    private final long jwtExpirationMs;

    public JwtService(RsaKeyProvider keyProvider) {
        this.keyProvider = keyProvider;
        Dotenv dotenv = Dotenv.configure().directory("./").ignoreIfMissing().load();
        this.jwtExpirationMs = Long.parseLong(dotenv.get("JWT_EXPIRATION_MS", "36000000"));
    }

    /**
     * Genere un token JWT avec les infos de l'user
     * @param userDetails Les infos de l'user
     * @return Le JWT en String
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Genere un token JWT avec les infos de l'user et des claims
     * @param extraClaims Les claims
     * @param userDetails Les infos de l'user
     * @return Le JWT en Sting
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(keyProvider.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    /**
     * Recup le mail de l'user avec le token
     * @param token le token
     * @return Le mail
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(keyProvider.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    /**
     * Verifie que le token est valide pour cette user
     * @param token Le token à tester
     * @param userDetails Les infos de l'User
     * @return true si valide, false sinon
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Check si le token est passé de date
     * @param token Le token a tester
     * @return true s'il est bon, false si plus consommable
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
