package com.example.boot.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.blog.exception.IdTypeException;
import com.example.boot.blog.formatter.IdType;
import com.example.boot.blog.model.dto.ArticleDTO;
import com.example.boot.blog.model.param.ArticleParam;
import com.example.boot.blog.model.po.ArticlePO;
import com.example.boot.blog.model.vo.ArticleVO;
import com.example.boot.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ArticleControoler
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 22:15
 * @Version 1.0
 */
@RequiredArgsConstructor        // 构造注入
@Controller
public class ArticleController {

    // 构造注入
    private final ArticleService articleService;

    /**
     * 首页
     * @param model
     * @return
     */
    @GetMapping(value = {"/", "/article/hot"})
    public String showArticle(Model model){
        List<ArticlePO> articlePOList = articleService.queryTopArticle();

        //转为VO  .hutool工具类
        List<ArticleVO> articleVOList = BeanUtil.copyToList(articlePOList, ArticleVO.class);
        //添加数据
        model.addAttribute("articleList", articleVOList);
        //视图
        return "/blog/articleList";
    }

    /**
     * 发布新文章
     * @param articleParam
     * @return
     */
    @PostMapping("/article/add")
    public String addArticle(@Validated(ArticleParam.AddArticle.class) ArticleParam articleParam){   // @Validated校验ArticleParam.class
        String title = articleParam.getTitle();
        String summary = articleParam.getSummary();
        String content = articleParam.getContent();

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle(title);
        articleDTO.setSummary(summary);
        articleDTO.setContent(content);

        boolean add = articleService.addArticle(articleDTO);

        return "redirect:/article/hot";
    }

    /**
     * 删除文章  ids= 1,6,2
     * @param idType
     * @return
     */
    @PostMapping("/article/remove")
    public String removeArticle(@RequestParam("ids") IdType idType){
//        System.out.println("ids = " + idType);     // ids = "5,7,8" 前端拿到是String "5,7,8"
        // 手动转类型成 IntegerList
        List<Integer> idList = new ArrayList<>();
        if (idType == null){
            throw new IdTypeException("ID为空");
        }

        boolean b = articleService.removeArticle(idList);
        return "redirect:/article/hot";
    }

    @GetMapping("/article/get")
    public String queryById(Integer id, Model model){
        if (id != null && id > 0) {
            ArticleDTO articleDTO = articleService.queryArticleById(id);

            //DTO-VO
            ArticleVO articleVO = BeanUtil.copyProperties(articleDTO, ArticleVO.class);
            //添加数据
            model.addAttribute("articleVO", articleVO);

            //视图
            return "/blog/editArticle";
        } else {
            return "/blog/error/error";
        }

    }

    @PostMapping("/article/edit")
    public String modifyArticle(@Validated({ArticleParam.EditArticle.class}) ArticleParam articleParam){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(articleParam.getId());
        articleDTO.setTitle(articleParam.getTitle());
        articleDTO.setSummary(articleParam.getSummary());
        articleDTO.setContent(articleParam.getContent());

        boolean edit = articleService.modifyArticle(articleDTO);

        return "redirect:/article/hot";
    }

    @GetMapping("article/detail/overview")
    @ResponseBody
    public String queryDetail(Integer id){

        String content = "无ID";
        if (id != null && id > 0){
            content = articleService.queryTop20Content(id);
        }

        return content;
    }
}
