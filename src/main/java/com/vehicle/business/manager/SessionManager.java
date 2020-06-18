package com.vehicle.business.manager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vehicle.framework.core.annotation.Component;

import java.time.Duration;

/**
 * @author HALOXIAO
 * @since 2020-06-18 21:38
 **/
@Component
public class SessionManager {

    private final LoadingCache<String, String> session;

    public SessionManager() {
        session = CacheBuilder.newBuilder().expireAfterAccess(Duration.ofHours(12)).build(
                new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key;
                    }
                }
        );
    }

    public boolean sessionExist(String cookie) {
        return session.getIfPresent(cookie) != null;
    }

    public void addSession(String username, String cookie) {
        session.put(cookie, username);
    }


}
