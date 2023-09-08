package com.sgg.springboot3.boot.mapper;

import com.sgg.springboot3.boot.entity.ArticleMany;
import com.sgg.springboot3.boot.entity.CommentMany;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

/**
 * ClassName: ArticleOneToManyMapper
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 13:55
 * @Version 1.0
 */
public interface ArticleOneToManyMapper {

    @Select("""
            select * from t_comment_many where article_id = #{articleId} order by id
            """)
    @Results(id = "commentMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "comment", property = "comment")
    })
    List<CommentMany> selectComments(Integer articleId);

    @Select("""
            select id, user_id, title, summary, read_count, create_time, update_time
            from t_article_many where id = #{articleId}
            """)
    @Results(id = "articleMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "summary", property = "summary"),
            @Result(column = "read_count", property = "readCount"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "id", property = "comments",
            many = @Many(select = "com.sgg.springboot3.boot.mapper.ArticleOneToManyMapper.selectComments",
                    fetchType = FetchType.LAZY))
    })
    ArticleMany getArticleById(Integer id);
}
