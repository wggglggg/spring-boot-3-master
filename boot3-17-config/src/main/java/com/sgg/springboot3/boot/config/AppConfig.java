package com.sgg.springboot3.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * ClassName: AppConfig
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-02 8:08
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "appconfig")
@PropertySource(value = "classpath:/app/app.properties")
@Data
public class AppConfig {

    private Integer appId;
    private String appName;
}
