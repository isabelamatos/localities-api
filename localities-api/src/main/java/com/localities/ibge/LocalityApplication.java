package com.localities.ibge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@EnableCaching
@EnableCircuitBreaker
@SpringBootApplication
public class LocalityApplication {
    private Logger log = LoggerFactory.getLogger(LocalityApplication.class);

    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    @CacheEvict(allEntries = true, value = { "getCityId" })
    @Scheduled(fixedDelay = 10 * 60 * 1000,  initialDelay = 500)
    public void reportCacheEvict() {
        log.info("Flushing cache at " +  new Date() + "...");
    }

    public static void main(String[] args) {
        SpringApplication.run(LocalityApplication.class, args);
    }
}
