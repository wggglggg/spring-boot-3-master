package com.example.boot.blog.model.po;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;

/**
 * ClassName: ArticlePO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 21:14
 * @Version 1.0
 */
@Data
public class ArticlePO {

    private Integer id;
    private Integer userId;
    private String title;
    private String summary;
    private Integer readCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
