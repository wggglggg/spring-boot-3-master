package com.example.boot.blog.mapper;

import com.example.boot.blog.model.ArticleAndDetailMap;
import com.example.boot.blog.model.po.ArticleDetailPO;
import com.example.boot.blog.model.po.ArticlePO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: ArticleMaper
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 21:54
 * @Version 1.0
 */

public interface ArticleMaper {

    //查询首页需要的文章列表
    @Select("""
            select id,user_id ,title,summary, read_count , create_time, update_time
                  from t_article_one
                  where read_count >= #{lowestReadCount}
                  order by read_count desc
                  limit #{articleNumsOnTop}
            """)
    @Results(id = "ArticleBaseMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "summary", property = "summary"),
            @Result(column = "read_count", property = "readCount"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<ArticlePO> topSortByReadCount(Integer lowestReadCount, Integer articleNumsOnTop);

    //两个表的连接
    @Select("""
            select a.id as id, title, summary, ad.content
            from t_article_one a join t_article_detail_one ad
            on a.id = ad.article_id
            where a.id = #{id}
            """)
    @Results(id = "ArticleAndDetailMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "summary", property = "summary"),
            @Result(column = "content", property = "content")
    })
    ArticleAndDetailMap selectArticleAndDetail(Integer id);

    // 新增新文章
    @Insert("""
            insert into t_article_one(user_id, title, summary, read_count, create_time, update_time)
            values(#{userId}, #{title}, #{summary}, #{readCount}, #{createTime}, #{updateTime})
            """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertArticle(ArticlePO articlePO);

    // 删除多个文章（文章列表）
    @Delete("""
            <script>
                delete from t_article_one where id in
                <foreach item="id" collection="list" open="(" separator="," close=")">
                    #{id}
                </foreach>
            </script>
            """)
    int deleteArticle(List<Integer> idList);

    //修改文章属性
    @Update("""
            update t_article_one set title = #{title}, summary = #{summary}, update_time = #{updateTime}
            where id = #{id}
            """)
    int updateArticle(ArticlePO articlePO);


    @Select("""
            select id, article_id, content from t_article_detail_one
            where article_id = #{id}
            """)
    @Results(id = "articleDetailMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "article_id", property = "articleId"),
            @Result(column = "content", property = "content")
    })
    ArticleDetailPO selectArticleDetailByArticleId(Integer id);
}
