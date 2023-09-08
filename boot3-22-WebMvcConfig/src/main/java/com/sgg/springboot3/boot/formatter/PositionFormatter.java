package com.sgg.springboot3.boot.formatter;

import com.sgg.springboot3.boot.entity.SatellitePosition;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Locale;
import java.util.StringJoiner;

/**
 * ClassName: PositionFormatter
 * Description:
 *      将请求参数String转为 SatellitePosition 实体类格式
 * @Author wggglggg
 * @Create 2023-09-06 20:24
 * @Version 1.0
 */
public class PositionFormatter implements Formatter<SatellitePosition>{

    //text表示请求参数的值,也就是前端用户输入的数据，通过此方法来解析，也让小完成了formatter格式化
    @Override
    public SatellitePosition parse(String text, Locale locale) throws ParseException {
        SatellitePosition satellitePosition = null;

        if (StringUtils.hasLength(text)){
            System.out.println("text = " + text);
            String[] items = text.split(";");
            satellitePosition = new SatellitePosition();
            satellitePosition.setItem1(items[0]);
            satellitePosition.setItem2(items[1]);
            satellitePosition.setItem3(items[2]);
            satellitePosition.setItem4(items[3]);
            satellitePosition.setItem5(items[4]);
        }
        System.out.println("text2 = " + text);
        return satellitePosition;
    }

    @Override
    public String print(SatellitePosition object, Locale locale) {
        StringJoiner joiner = new StringJoiner("#");
        joiner.add(object.getItem1()).add(object.getItem2()).add(object.getItem3())
                .add(object.getItem4());

        return joiner.toString();
    }

}
