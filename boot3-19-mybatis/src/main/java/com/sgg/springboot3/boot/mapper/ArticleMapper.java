package com.sgg.springboot3.boot.mapper;

import com.sgg.springboot3.boot.entity.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.data.relational.core.sql.In;

/**
 * ClassName: ArticleMappler
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-02 19:07
 * @Version 1.0
 */
public interface ArticleMapper {
    // 查询某ID的文章
    @Select("""
            select * from t_article where id = #{id}    
            """)
    @Results(id = "ArticleMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "author", property = "author"),
            @Result(column = "publish_time", property = "publishTime"),
            @Result(column = "comment_time", property = "commentTime")
    })
    Article selectArticleById(Integer id);

    // 添加新文章
    @Insert("""
            insert into t_article 
            values (null, #{title}, #{author}, #{publishTime}, #{commentTime})
            """)
    int insertArticle(Article article);

    // 修改某ID文章
    @Update("""
            update t_article set title = #{title}, author = #{author},
             publish_time = #{publishTime}, comment_time = #{commentTime} where id = #{id}
            """)
    int updateArticle(Article article);

    @Delete("""
            delete from t_article where id = #{id}
            """)
    int deleteArticle(Integer id);
}
