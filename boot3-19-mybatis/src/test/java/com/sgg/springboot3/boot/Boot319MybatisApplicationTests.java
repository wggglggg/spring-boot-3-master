package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.controller.ArticleController;
import com.sgg.springboot3.boot.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
class Boot319MybatisApplicationTests {

    @Autowired
    private ArticleController articleController;

    @Test
    void testSelect() {
        Article article = articleController.getArticleById(1);
        System.out.println("article = " + article);
    }

    @Test
    public void testInsert(){
        Article article =
                new Article(null,
                        "Vue3",
                        "无名",
                        LocalDateTime.parse("2020-12-14T12:00:50"),
                        LocalDateTime.parse("2020-12-14T12:00:50"));
        int count = articleController.insertArticle(article);
        System.out.println("count = " + count);
    }

    @Test
    public void testUpdate(){
        Article article =
                new Article(3,
                        "Vue3",
                        "菜菜子",
                        LocalDateTime.parse("2020-12-14T12:00:50"),
                        LocalDateTime.parse("2020-12-14T12:00:50"));

        int count = articleController.updateArticle(article);
        System.out.println("count = " + count);
    }

    @Test
    public void testDelete(){
        int count = articleController.deleteArticle(3);

        System.out.println("count = " + count);
    }
}
