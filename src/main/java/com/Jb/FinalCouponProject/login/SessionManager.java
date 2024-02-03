package com.Jb.FinalCouponProject.login;

import com.Jb.FinalCouponProject.exceptions.NoClientSessionException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component

public class SessionManager {
    private final Map<String, ClientSession> tokens;
    private long expirationPeriodMinutes;

    public SessionManager(Map<String, ClientSession> tokens,
                          @Value("${token.expiration.minutes}") long expirationPeriodMinutes) {
        this.tokens = tokens;
        this.expirationPeriodMinutes = expirationPeriodMinutes;
    }

    public UUID handleToken(String token) {
        ClientSession clientSession = tokens.get(token);

        if (clientSession == null||clientSession.expired()) {
            throw new NoClientSessionException(String.format("There is no session for %s", token));
        }

        return clientSession.accessOrThrow()
                .getUuid();
    }
}


