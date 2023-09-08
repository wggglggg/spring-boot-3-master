package com.sgg.springboot3.boot3.config;

import com.sgg.springboot3.boot3.entiry.BookRecord;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * ClassName: BookContainer
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-07 22:21
 * @Version 1.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "product")
public class BookContainer {

    private List<BookRecord> books;
}
