package com.sgg.springboot3.boot.mapper;

import com.sgg.springboot3.boot.entity.ArticleDetailOne;
import com.sgg.springboot3.boot.entity.ArticleOne;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

/**
 * ClassName: ArticleOneToOneMapper
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-03 10:22
 * @Version 1.0
 */
public interface ArticleOneToOneMapper {

    //一对一查询


    //查询文章详情
    @Select("""
            select * from t_article_detail_one where article_id = #{articleId}
            """)
    @Results( {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "content", property = "content")
    })
    ArticleDetailOne selectDetail(Integer articleId);

    //查询 文章的属性包含详情, 也包含文章内容content
    @Select("""
            select id, user_id, title, summary, read_count, create_time, update_time
             from t_article_one where id = #{id}
            """)
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "summary", property = "summary"),
            @Result(column = "read_count", property = "readCount"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            // column要与表字段中的一致
            @Result(column = "id", property = "articleDetailOne",
            one = @One(select = "com.sgg.springboot3.boot.mapper.ArticleOneToOneMapper.selectDetail", fetchType = FetchType.LAZY))
    })
    ArticleOne selectArticle(Integer id);
}
