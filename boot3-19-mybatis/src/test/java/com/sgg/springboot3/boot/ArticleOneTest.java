package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.controller.ArticleController;
import com.sgg.springboot3.boot.controller.ArticleOneController;
import com.sgg.springboot3.boot.entity.ArticleDetailOne;
import com.sgg.springboot3.boot.entity.ArticleOne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: ArticleOneTest
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 10:59
 * @Version 1.0
 */
@SpringBootTest
public class ArticleOneTest {

    @Autowired
    private ArticleOneController articleOneController;

    @Test
    public void testArticleOne(){
        ArticleOne articleOne = articleOneController.getArticleAll(1);
        System.out.println("articleOne = " + articleOne);
    }

    @Test
    public void testArticleDetailOne(){
        articleOneController.getArticleDetail(1);

    }
}
