package com.sgg.springboot3.boot.modle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Todo
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 10:55
 * @Version 1.0
 */
@Data
public class Todo {
    private Integer userId;

    private Integer id;

    private String title;

    private Boolean completed;
}
