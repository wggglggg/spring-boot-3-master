package com.example.boot.blog.formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * ClassName: IdTypeFormatter
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-11 17:26
 * @Version 1.0
 */
public class IdTypeFormatter implements Formatter<IdType> {
    @Override
    public IdType parse(String text, Locale locale) throws ParseException {
        List<Integer> ids = null;
        if (StringUtils.hasText(text)) {
            ids = new ArrayList<>();
            for (String id : text.split(",")) {
                ids.add(Integer.valueOf(id));
            }
        }
        IdType idType = new IdType();
        idType.setIdList(ids);

        return idType;
    }

    @Override
    public String print(IdType object, Locale locale) {
        return null;
    }
}
