package com.sgg.springboot3.boot.entity;

import lombok.Data;

/**
 * ClassName: CommentMany
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 13:52
 * @Version 1.0
 */
@Data
public class CommentMany {

    private Integer id;
    private Integer articleId;
    private String comment;
}
