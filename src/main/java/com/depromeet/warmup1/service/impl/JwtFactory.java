package com.depromeet.warmup1.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.depromeet.warmup1.config.JwtConfig;
import com.depromeet.warmup1.entity.Member;
import com.depromeet.warmup1.exception.JWTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class JwtFactory {

    private static final String HEADER_PREFIX = "Bearer ";

    private final JWTVerifier jwtVerifier;
    private final JwtConfig.JwtSettings jwtSettings;


    public String generateToken(Member member) {
        String token;

        LocalDateTime currentTime = LocalDateTime.now();

        token = JWT.create()
                .withIssuer(jwtSettings.getTokenIssuer())
                .withClaim("USERNAME", member.getName())
                .withClaim("ID", member.getMid())
                .withIssuedAt(java.sql.Timestamp.valueOf(currentTime))
                .withExpiresAt(java.sql.Timestamp.valueOf(
                        currentTime.plusMinutes(jwtSettings.getTokenExpirationTime())
                ))
                .sign(Algorithm.HMAC256(jwtSettings.getTokenSigningKey()));


        return token;

    }

    public String generateRefreshToken(Member member) {
        String token;

        LocalDateTime currentTime = LocalDateTime.now();

        token = JWT.create()
                .withIssuer(jwtSettings.getTokenIssuer())
                .withKeyId(UUID.randomUUID().toString())
                .withClaim("USERNAME", member.getName())
                .withClaim("ID", member.getMid())
                .withIssuedAt(java.sql.Timestamp.valueOf(currentTime))
                .withExpiresAt(java.sql.Timestamp.valueOf(
                        currentTime.minusDays(jwtSettings.getRefreshTokenExpTime())
                ))
                .sign(Algorithm.HMAC256(jwtSettings.getTokenSigningKey()));


        return token;

    }

    public Optional<Long> getMemberId(String token) {
        if (StringUtils.isEmpty(token)) {
            return Optional.empty();
        }
        try {
            return decodeToken(token);
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    private Optional<Long> decodeToken(String header) {

        String token;
        try {
            token = tokenExtractor(header);
        } catch (IllegalArgumentException e) {
            log.warn("Failed to extract token from header. header:" + header, e);
            throw new JWTException("Failed to extract token from header. header:" + header);
        }

        DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (Exception e) {
            throw new JWTException(e.getMessage());
        }

        Map<String, Claim> claims = decodedJWT.getClaims();
        Claim idClaim = claims.get("ID");
        if (idClaim == null) {
            log.warn("Failed to decode jwt token. header:" + header);
            throw new JWTException("Failed to decode jwt token. header:" + header);
        }

        return Optional.of(idClaim.asLong());
    }

    private String tokenExtractor(String header) {
        if (StringUtils.isEmpty(header)) {
            throw new IllegalArgumentException("Authorization header가 없습니다.");
        }

        if (header.length() < HEADER_PREFIX.length()) {
            throw new IllegalArgumentException("authorization header size가 옳지 않습니다. header의 길이는 " + HEADER_PREFIX.length() + " 보다 크거나 같아야 합니다.");
        }

        if (!header.startsWith(HEADER_PREFIX)) {
            throw new IllegalArgumentException("올바른 header형식이 아닙니다.");
        }

        return header.substring(HEADER_PREFIX.length());
    }


}
