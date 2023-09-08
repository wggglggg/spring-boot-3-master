package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.controller.ArticleManyController;
import com.sgg.springboot3.boot.entity.ArticleMany;
import com.sgg.springboot3.boot.entity.CommentMany;
import com.sgg.springboot3.boot.mapper.ArticleOneToManyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName: ArticleManyTest
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 14:23
 * @Version 1.0
 */
@SpringBootTest
public class ArticleManyTest {

    @Autowired
    private ArticleManyController articleManyController;

    @Autowired
    private ArticleOneToManyMapper articleOneToManyMapper;


    @Test
    public void testCommentMany(){
        articleManyController.getComments(1);
    }

    @Test
    public void testArticleMany(){


        System.out.println("article = " + articleManyController.getArticle(1));
    }
}
