package com.project.hw5.Services;


import com.project.hw5.Entities.SessionEntity;
import com.project.hw5.Entities.User;
import com.project.hw5.Repositories.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {

    private final String secretKey;
    private final SessionRepository sessionRepository;

    @Autowired
    public JwtService(@Value("${jwt.secret.key}") String secretKey, SessionRepository sessionRepository) {
        this.secretKey = secretKey;
        this.sessionRepository = sessionRepository;
    }

    public String generateJwt(User user) {
        // 🔴 Remove previous session (if exists)
        sessionRepository.deleteByUser(user);

        // ✅ Generate new token
        String token = Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(60 * 60))) // 1-hour expiry
                .claim("email", user.getEmail())
                .signWith(getSigningKey())
                .compact();

        // ✅ Store new session in DB


        SessionEntity session = new SessionEntity(token, user);
        sessionRepository.save(session);


        return token;
    }

    public boolean validateJwt(String token) {
        Optional<SessionEntity> session = sessionRepository.findByToken(token);
        return session.isPresent() && !isTokenExpired(token);
    }

    public Long getIdFromJwt(String token) {
        return Long.valueOf(extractClaims(token).getSubject());
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(Date.from(Instant.now()));
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
