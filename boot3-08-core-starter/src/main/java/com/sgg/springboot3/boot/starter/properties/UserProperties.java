package com.sgg.springboot3.boot.starter.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: User
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-25 10:54
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "user")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProperties {

    private String userName;
    private String password;
}
