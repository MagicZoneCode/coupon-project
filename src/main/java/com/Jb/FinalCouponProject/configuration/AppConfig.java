package com.Jb.FinalCouponProject.configuration;

import com.Jb.FinalCouponProject.login.ClientSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Map<String, ClientSession> tokens() {
        return new HashMap<>();
    }
}

