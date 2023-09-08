package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.config.AppConfig;
import com.sgg.springboot3.boot.config.CarConfig;
import com.sgg.springboot3.boot.config.TvConfig;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.StringJoiner;

@SpringBootTest
class Boot317ConfigApplicationTests {

    @Autowired
    private AppConfig appConfig;
//    @Autowired
//    private CarConfig carConfig;
    @Autowired
    private TvConfig tvConfig;


    /**
     * 导入配置 方式一
     * 测试   @ConfigurationProperties(prefix = "carconfig")   指定配置开头前缀
     * 与    @Configuration
     */
    @Test
    void test1() {
        System.out.println("appConfig.getAppName() = " + appConfig.getAppName());
        System.out.println("appConfig.getAppId() = " + appConfig.getAppId());
    }

    /**
     * 导入配置 方式二
     * @ConfigurationPropertiesScan("com.sgg.springboot3.boot.config")  扫描xxxx包下的xxxConfig文件，一般放在入口类上,无法搭配自定义配置文件路径@PropertySource(value = {"classpath:/car/car.yml"})
     * @ConfigurationProperties(prefix = "carconfig")   指定配置开头前缀
     */
//    @Test
//    void test2() {
//
//        System.out.println("carConfig.getColor = " + carConfig.getColor());
//        System.out.println("carConfig.getSeatNum() = " + carConfig.getSeatNum());
//    }


    /**
     * 导入配置 方式三
     * @
     */
    @Test
    void test3() {
        System.out.println("TvConfig.getBrand() = " + tvConfig.getBrand());
        System.out.println("tvConfig.getInch() = " + tvConfig.getInch());
    }
}
