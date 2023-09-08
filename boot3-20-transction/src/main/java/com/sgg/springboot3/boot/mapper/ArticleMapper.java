package com.sgg.springboot3.boot.mapper;

import com.sgg.springboot3.boot.entity.Article;
import com.sgg.springboot3.boot.entity.ArticleDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Results;

/**
 * ClassName: ArticleMapper
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 21:27
 * @Version 1.0
 */
public interface ArticleMapper {

    @Insert("""
            insert into t_article_one (user_id, title, summary, read_count, create_time, update_time)
            values(#{userId}, #{title}, #{summary}, #{readCount}, #{createTime}, #{updateTime})
            """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertArticle(Article article);

    @Insert("""
            insert into t_article_detail_one (article_id, content)
            values(#{articleId}, #{content})
            """)
    int insertArticleDetail(ArticleDetail articleDetail);
}
