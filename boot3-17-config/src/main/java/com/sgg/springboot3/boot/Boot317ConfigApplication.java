package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.config.AppConfig;
import com.sgg.springboot3.boot.config.CarConfig;
import com.sgg.springboot3.boot.config.TvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@ConfigurationPropertiesScan("com.sgg.springboot3.boot.config")
//@EnableConfigurationProperties({TvConfig.class, CarConfig.class, AppConfig.class})
@SpringBootApplication
public class Boot317ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot317ConfigApplication.class, args);
    }

}
