package com.sgg.springboot3.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ArticleDetail
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 21:06
 * @Version 1.0
 */
@Data
public class ArticleDetail {
    private Integer id;
    private Integer articleId;
    private String content;
}
