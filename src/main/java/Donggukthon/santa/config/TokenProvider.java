package Donggukthon.santa.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenProvider {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String userEmail) {

        Claims claims = Jwts.claims();
        claims.put("userEmail", userEmail);

        String jwtToken = Jwts.builder()
                //.setSubject(useremail)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();
        return jwtToken;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String verifyToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        // 여기서 claims에서 필요한 정보 추출 가능
        Claims body = claims.getBody();
        String userEmail = body.get("userEmail").toString();
        return userEmail;
    }
}
