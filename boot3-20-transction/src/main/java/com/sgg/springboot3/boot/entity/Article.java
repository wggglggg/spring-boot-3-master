package com.sgg.springboot3.boot.entity;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;

/**
 * ClassName: Article
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 21:06
 * @Version 1.0
 */
@Data
public class Article {
    private Integer id;
    private Integer userId;
    private String title;
    private String summary;     // 文章概述
    private Integer readCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 详情属性
    private ArticleDetail articleDetail;
}
