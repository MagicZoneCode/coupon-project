package com.Jb.FinalCouponProject.login;

import com.Jb.FinalCouponProject.exceptions.TokenExpiredException;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ClientSession {
    private final UUID uuid;
    private long lastAccessMillis;
    private final int tokenExpirationMinutes;


    private ClientSession(UUID uuid, long lastAccessMillis, int tokenExpirationMinutes) {
        this.uuid = uuid;
        this.lastAccessMillis = lastAccessMillis;
        this.tokenExpirationMinutes = tokenExpirationMinutes;
    }

    public UUID getUuid() {
        return uuid;
    }

    public static ClientSession ofNow(UUID uuid, int tokenExpirationMinutes) {
        return new ClientSession(uuid, System.currentTimeMillis(), tokenExpirationMinutes);
    }

    public boolean expired() {
        long idleTimeMillis = System.currentTimeMillis();
        long idleTimeMinutes = TimeUnit.MILLISECONDS.toMinutes(idleTimeMillis - lastAccessMillis);
        return idleTimeMinutes > tokenExpirationMinutes;
    }

    public ClientSession accessOrThrow() {
        if (expired()) {
            throw new TokenExpiredException("Token expired!");
        }
        lastAccessMillis = System.currentTimeMillis();
        return this;
    }
}

