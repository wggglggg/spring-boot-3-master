package com.sgg.springboot3.boot.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: ArticleMany
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 13:53
 * @Version 1.0
 */
@Data
public class ArticleMany {

    private Integer id;
    private String userId;
    private String title;
    private String summary;
    private Integer readCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // one to one
    private List<CommentMany> comments;

}
