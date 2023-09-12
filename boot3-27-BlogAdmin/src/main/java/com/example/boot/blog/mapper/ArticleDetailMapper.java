package com.example.boot.blog.mapper;

import com.example.boot.blog.model.po.ArticleDetailPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ClassName: ArticleDetailMapper
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-09 17:23
 * @Version 1.0
 */
public interface ArticleDetailMapper {

    @Insert("""
            insert into t_article_detail_one(article_id, content)
            values(#{articleId}, #{content})
            """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertArticleDetail(ArticleDetailPO articleDetailPO);

    //删除文章内容
    @Delete("""
            <script>
                delete from t_article_detail_one where article_id in
                <foreach item="id" collection="list" open="(" separator="," close=")">
                    #{id}
                </foreach>
            </script>
            """)
    int deleteDetail(List<Integer> idList);

    //更新文章内容
    @Update("""
            update t_article_detail_one set content = #{content} where article_id = #{articleId}
            """)
    int updateArticleDetail(ArticleDetailPO articleDetailPO);

}
