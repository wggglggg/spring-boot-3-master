package com.example.boot.blog.formatter;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

/**
 * ClassName: IdType
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-09 21:22
 * @Version 1.0
 */
@Data
public class IdType {
    private List<Integer> idList;
}
