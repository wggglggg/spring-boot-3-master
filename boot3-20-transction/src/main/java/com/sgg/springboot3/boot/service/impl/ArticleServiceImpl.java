package com.sgg.springboot3.boot.service.impl;

import com.sgg.springboot3.boot.entity.Article;
import com.sgg.springboot3.boot.entity.ArticleDetail;
import com.sgg.springboot3.boot.mapper.ArticleMapper;
import com.sgg.springboot3.boot.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: ArticleServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 21:51
 * @Version 1.0
 */
@Transactional      // 事务配置
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public boolean insertArticle(Article article, String content) {
        //添加新的文章
        int rows = articleMapper.insertArticle(article);

        int i = 10 / 0;     // 手动在第一语句执行后制造一个错误

        //添加文章内容
        ArticleDetail articleDetail = new ArticleDetail();
        //使用了@Options(useGeneratedKeys),就带有id值了
        articleDetail.setArticleId(article.getId());
        articleDetail.setContent(content);
        int articleDetailRows = articleMapper.insertArticleDetail(articleDetail);

        return (rows + articleDetailRows) == 2;
    }
}
