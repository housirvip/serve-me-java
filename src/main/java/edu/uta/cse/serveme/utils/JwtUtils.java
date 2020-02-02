package edu.uta.cse.serveme.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.uta.cse.serveme.constant.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Slf4j
@RequiredArgsConstructor
public class JwtUtils {

    private final String secret;

    private final Long expire;

    private final Long delay;

    public String encode(Integer uid, List<String> role) {

        String jwt = null;
        String[] roles = new String[role.size()];
        try {
            jwt = JWT.create()
                    .withClaim(Constant.UID, uid)
                    .withArrayClaim(Constant.ROLE, role.toArray(roles))
                    .withExpiresAt(new Date(System.currentTimeMillis() + expire * 1000))
                    .sign(Algorithm.HMAC256(secret));
        } catch (RuntimeException e) {
            log.error("Jwt: encode failed ", e);
        }

        return jwt;
    }

    public DecodedJWT decode(String token) {

        DecodedJWT decode = null;
        try {
            decode = JWT.require(Algorithm.HMAC256(secret))
                    .acceptExpiresAt(delay)
                    .build()
                    .verify(token);
        } catch (RuntimeException e) {
            if (e instanceof JWTVerificationException) {
                log.info("Jwt: verify failed, " + e.getMessage());
            } else {
                log.error("Jwt: decode failed ", e);
            }
        }

        return decode;
    }
}
