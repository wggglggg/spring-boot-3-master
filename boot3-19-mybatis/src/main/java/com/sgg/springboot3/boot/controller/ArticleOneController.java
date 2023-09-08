package com.sgg.springboot3.boot.controller;

import com.sgg.springboot3.boot.entity.ArticleDetailOne;
import com.sgg.springboot3.boot.entity.ArticleOne;
import com.sgg.springboot3.boot.mapper.ArticleOneToOneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;

/**
 * ClassName: ArticleOneController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 10:57
 * @Version 1.0
 */
@Controller
public class ArticleOneController {

    @Autowired
    private ArticleOneToOneMapper articleOneToOneMapper;

    public ArticleOne getArticleAll(Integer articleId){
        return articleOneToOneMapper.selectArticle(articleId);
    }

    public ArticleDetailOne getArticleDetail(Integer articleId){
        return articleOneToOneMapper.selectDetail(articleId);
    }
}
