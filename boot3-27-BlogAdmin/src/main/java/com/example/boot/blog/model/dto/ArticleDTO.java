package com.example.boot.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ArticleDTO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-09 16:57
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleDTO {

    private Integer id;
    private String title;
    private String summary;
    private String content;
}
