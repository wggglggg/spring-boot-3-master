package com.sgg.springboot3.boot.contoller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ArticleInterceptor
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 10:46
 * @Version 1.0
 */
@RestController
public class ArticleInterceptor {

    @GetMapping("/article/query")
    public String query(){
        return "查询文章";
    }

    @PostMapping("/article/add")
    public String addArticle(){
        return "发布新文章";
    }

    @PostMapping("/article/edit")
    public String editArticle(){
        return "修改文章";
    }

    @DeleteMapping("/article/remove")
    public String remove(){
        return "删除文章";
    }

}
