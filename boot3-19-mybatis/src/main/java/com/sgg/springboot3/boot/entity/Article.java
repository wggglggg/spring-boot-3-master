package com.sgg.springboot3.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;

/**
 * ClassName: Article
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-02 18:52
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private String author;
    private LocalDateTime publishTime;
    private LocalDateTime commentTime;

}
