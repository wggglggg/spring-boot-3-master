package com.sgg.springboot3.boot.vo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * ClassName: OrderVo
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 19:18
 * @Version 1.0
 */
@Data
public class OrderVo {

    @NotNull(message = "id不能为空")
    private Integer id;
    @NotBlank(message = "订单名称不能为空")
    private String name;
    @NotNull(message = "商品数量必须有值")
    @Range(min = 1, max = 10000, message = "一个订单商品数量在{min}-{max}之间")
    private Integer amount;
    @NotNull(message = "用户id不能为空")
    @Min(value = 1, message = "从{value}开始")
    private Integer userId;
}
