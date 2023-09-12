package com.example.boot.blog.model.param;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ClassName: ArticleParam
 * Description:
 * 发布新文章时，从网页接受到的几个属性
 *
 * @Author wggglggg
 * @Create 2023-09-09 16:37
 * @Version 1.0
 */
@Data
public class ArticleParam {

    // 组， 限制检验Validate此实体类属性时时
    public static interface AddArticle {};
    public static interface EditArticle {};

    @NotNull(message =  "修改时必须有id", groups = {EditArticle.class})
    @Min(value = 1,  message = "文章id大于{value}", groups = {EditArticle.class})
    private Integer id;

    @NotBlank(message = "请输入文章标题", groups = {AddArticle.class, EditArticle.class})
    @Size(message = "文章标题在{min}-{max}", min = 1, max = 20, groups = {AddArticle.class, EditArticle.class})
    private String title;

    @NotBlank(message = "请输入文章副标题", groups = {AddArticle.class, EditArticle.class})
    @Size(min = 10, max = 30, message = "文章副标题在{min}-{max}", groups = {AddArticle.class, EditArticle.class})
    private String summary;

    @NotBlank(message = "请输入文章内容", groups = {AddArticle.class, EditArticle.class})
    @Size(min = 10, max = 8000, message = "文章内容至少{min}字，最多{max}字", groups = {AddArticle.class, EditArticle.class})
    private String content;
}
