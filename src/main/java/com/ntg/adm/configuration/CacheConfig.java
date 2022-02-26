package com.ntg.adm.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.time.Period;

@Configuration
@EnableCaching
public class CacheConfig {

    static int  x;

    public static void main(String[] args) {
        Period period = Period.of(0, 0, 0);
        System.out.println(period);
    }
}
