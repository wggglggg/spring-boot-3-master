package com.example.boot.blog.model.po;

import lombok.Data;

/**
 * ClassName: ArticleDetailPO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 21:14
 * @Version 1.0
 */
@Data
public class ArticleDetailPO {

    private Integer id;
    private Integer articleId;
    private String content;
}
