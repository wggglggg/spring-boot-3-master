package com.example.boot.blog.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ClassName: ArticleVO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 22:42
 * @Version 1.0
 */
@Data
public class ArticleVO {

    private Integer id;
    private Integer userId;
    private String title;
    private String summary;
    private Integer readCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String content;
}
