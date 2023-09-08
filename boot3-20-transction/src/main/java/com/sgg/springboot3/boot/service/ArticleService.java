package com.sgg.springboot3.boot.service;

import com.sgg.springboot3.boot.entity.Article;
import org.springframework.stereotype.Service;

/**
 * ClassName: ArticleService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 21:28
 * @Version 1.0
 */
public interface ArticleService {

    boolean insertArticle(Article article, String content);
}
