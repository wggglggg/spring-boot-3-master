package com.sgg.springboot3.boot.entity;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;

/**
 * ClassName: ArticleOne
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 9:48
 * @Version 1.0
 */
@Data
public class ArticleOne {

    private Integer id;
    private String userId;
    private String title;
    private String summary;
    private Integer readCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // one to one
    private ArticleDetailOne articleDetailOne;

}
