package com.sgg.springboot3.boot.config;

import com.sgg.springboot3.boot.factory.CompositePropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * ClassName: TvConfig
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-02 9:55
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "tv.config")
// 默认自定义只支持Properties，一定要使用yaml，就要重写public class CompositePropertySourceFactory extends DefaultPropertySourceFactory {}
@PropertySource(value = "classpath:/tv/tv.yml", factory = CompositePropertySourceFactory.class)
@Data
public class TvConfig {
    private String brand;
    private Double inch;
}
