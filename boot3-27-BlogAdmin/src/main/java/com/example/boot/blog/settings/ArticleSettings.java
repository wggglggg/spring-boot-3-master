package com.example.boot.blog.settings;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: ArticleSettings
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 21:50
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "article")
@Data
public class ArticleSettings {

    // 被阅读次数最低阀值
    private Integer lowestReadCount;
    // 首页最多显示多少篇文章阈值
    private Integer articleNumsOnTop;
}
