package com.sgg.springboot3.boot.controller;

import com.sgg.springboot3.boot.entity.ArticleMany;
import com.sgg.springboot3.boot.entity.CommentMany;
import com.sgg.springboot3.boot.mapper.ArticleOneToManyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * ClassName: ArticleManyController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 14:24
 * @Version 1.0
 */
@Controller
public class ArticleManyController {

    @Autowired
    private ArticleOneToManyMapper articleOneToManyMapper;

    public List<CommentMany> getComments(Integer articleId){
        return articleOneToManyMapper.selectComments(articleId);
    }

    public ArticleMany getArticle(Integer articleId){
        return articleOneToManyMapper.getArticleById(1);
    }
}
