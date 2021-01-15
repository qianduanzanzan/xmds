package com.atxiaoming.config;

import com.atxiaoming.entity.OssTemplate;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class AppProjectConfig {
    @Bean
    @ConfigurationProperties(prefix = "oss")
    public OssTemplate ossTemplate() {
        return new OssTemplate();
    }
}
