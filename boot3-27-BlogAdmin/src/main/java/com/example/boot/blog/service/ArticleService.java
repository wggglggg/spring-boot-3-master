package com.example.boot.blog.service;

import com.example.boot.blog.model.dto.ArticleDTO;
import com.example.boot.blog.model.po.ArticlePO;
import com.example.boot.blog.service.impl.ArticleServiceImpl;

import java.util.List;

/**
 * ClassName: ArticleService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 22:16
 * @Version 1.0
 */
public interface ArticleService {

    /**
     * 获取首页文章列表
     * @return
     */
    List<ArticlePO> queryTopArticle();

    /**
     * //根据主键查询文章
     * @param id
     * @return
     */
    ArticleDTO queryArticleById(Integer id);

    /**
     * 发布文章（article ，article_detail）
     * @param articleDTO
     * @return
     */
    boolean addArticle(ArticleDTO articleDTO);

    /**
     * 删除多篇文章(文章列表List)
     * @param idList
     * @return
     */
    boolean removeArticle(List<Integer> idList);

    /**
     *  //修改文章属性和内容
     * @param articleDTO
     * @return
     */
    boolean modifyArticle(ArticleDTO articleDTO);

    /**
     * 查询文章内容前20个字符
     * @param id
     * @return
     */
    String queryTop20Content(Integer id);
}
