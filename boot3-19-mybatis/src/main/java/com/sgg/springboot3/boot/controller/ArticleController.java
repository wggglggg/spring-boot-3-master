package com.sgg.springboot3.boot.controller;

import com.sgg.springboot3.boot.entity.Article;
import com.sgg.springboot3.boot.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;

/**
 * ClassName: ArticleController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-02 19:22
 * @Version 1.0
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleMapper articleMapper;

    // 查询某个文章
    public Article getArticleById(Integer id){
        Article article = articleMapper.selectArticleById(id);
        return article;
    }

    // 增加一篇新文章
    public int insertArticle(Article article){
        int count = articleMapper.insertArticle(article);
        return count;
    }

    // 修改
    public int updateArticle(Article article){
        int count = articleMapper.updateArticle(article);
        return count;
    }

    //删除
    public int deleteArticle(Integer id){
        int count = articleMapper.deleteArticle(id);

        return count;
    }
}
