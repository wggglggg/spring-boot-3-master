package com.example.boot.blog.service.impl;

import com.example.boot.blog.model.ArticleAndDetailMap;
import com.example.boot.blog.model.dto.ArticleDTO;
import com.example.boot.blog.model.po.ArticleDetailPO;
import com.example.boot.blog.model.po.ArticlePO;
import com.example.boot.blog.service.ArticleService;
import com.example.boot.blog.settings.ArticleSettings;
import com.example.boot.blog.mapper.ArticleDetailMapper;
import com.example.boot.blog.mapper.ArticleMaper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * ClassName: ArticleServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 22:23
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor    // 不用构造器注入
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMaper articleMaper;
    private final ArticleDetailMapper articleDetailMapper;
    private final ArticleSettings articleSettings;


    // 构造器注入
//    public ArticleServiceImpl(ArticleSettings articleSettings) {
//        this.articleSettings = articleSettings;
//    }
//
//    public ArticleServiceImpl(ArticleMaper articleMaper) {
//        this.articleMaper = articleMaper;
//    }

    /**
     * 查询热点文章
     * @return
     */
    @Override
    public List<ArticlePO> queryTopArticle() {
        Integer articleNumOnTop = articleSettings.getArticleNumsOnTop();
        Integer lowestReadCount = articleSettings.getLowestReadCount();

        return articleMaper.topSortByReadCount(lowestReadCount, articleNumOnTop);
    }

    @Override
    public ArticleDTO queryArticleById(Integer id) {
        //文章属性，内容
        ArticleAndDetailMap articleAndDetailMap = articleMaper.selectArticleAndDetail(id);
        //转为DTO
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(articleAndDetailMap.getId());
        articleDTO.setTitle(articleAndDetailMap.getTitle());
        articleDTO.setSummary(articleAndDetailMap.getSummary());
        articleDTO.setContent(articleAndDetailMap.getContent());

        return articleDTO;
    }

    /**
     发布文章（article ，article_detail）
     * @param articleDTO
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean addArticle(ArticleDTO articleDTO) {
        ArticlePO articlePO = new ArticlePO();
        articlePO.setUserId(new Random().nextInt(5000));
        articlePO.setTitle(articleDTO.getTitle());
        articlePO.setSummary(articleDTO.getSummary());
        articlePO.setReadCount(new Random().nextInt(1000));
        articlePO.setCreateTime(LocalDateTime.now());
        articlePO.setUpdateTime(LocalDateTime.now());
        int articleRows = articleMaper.insertArticle(articlePO);

        //文章详情
        ArticleDetailPO articleDetailPO = new ArticleDetailPO();
        articleDetailPO.setArticleId(articlePO.getId());
        articleDetailPO.setContent(articleDTO.getContent());
        int articleDetailRows = articleDetailMapper.insertArticleDetail(articleDetailPO);

        return (articleRows + articleDetailRows) == 2 ? true : false;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean removeArticle(List<Integer> idList) {
        int articleRows = articleMaper.deleteArticle(idList);
        int articleDetailRows = articleDetailMapper.deleteDetail(idList);

        return (articleRows == articleDetailRows) ? true : false;
    }

    /**
     * 修改文章与文章content内容
     * @param articleDTO
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean modifyArticle(ArticleDTO articleDTO) {
        ArticlePO articlePO = new ArticlePO();
        articlePO.setId(articleDTO.getId());
        articlePO.setTitle(articleDTO.getTitle());
        articlePO.setSummary(articleDTO.getSummary());
        articlePO.setUpdateTime(LocalDateTime.now());
        int articleRows = articleMaper.updateArticle(articlePO);

        ArticleDetailPO articleDetailPO = new ArticleDetailPO();
        articleDetailPO.setArticleId(articleDTO.getId());
        articleDetailPO.setContent(articleDTO.getContent());
        int articleDetailRows = articleDetailMapper.updateArticleDetail(articleDetailPO);

        return (articleRows + articleDetailRows) == 2 ? true : false;
    }

    /**
     * 文章内容 20
     * @param id
     * @return
     */
    @Override
    public String queryTop20Content(Integer id) {
        ArticleDetailPO articleDetailPO = articleMaper.selectArticleDetailByArticleId(id);
        String content = "无内容";
        if (articleDetailPO != null){
            content = articleDetailPO.getContent();
            if (StringUtils.hasText(content)){
                content = content.substring(0, 20);
            }
        }

        return content;
    }
}
