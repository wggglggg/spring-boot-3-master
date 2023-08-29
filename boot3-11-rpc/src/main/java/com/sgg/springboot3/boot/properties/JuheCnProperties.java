package com.sgg.springboot3.boot.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: JuheProperties
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 13:33
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "juhe.cn")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JuheCnProperties {

    // 查天气预报
    private String weatherKey;
    private String weatherCity;

    // 查手机归属地信息
    private String mobileKey;

    // 查星座运势
    private String consKey;

    // 标准体重计算
    private String weightKey;
}
