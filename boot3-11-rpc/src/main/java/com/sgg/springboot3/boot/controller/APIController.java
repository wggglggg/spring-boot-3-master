package com.sgg.springboot3.boot.controller;

import com.sgg.springboot3.boot.properties.JuheCnProperties;
import com.sgg.springboot3.boot.service.ConstellationService;
import com.sgg.springboot3.boot.service.MobileService;
import com.sgg.springboot3.boot.service.WeatherService;
import com.sgg.springboot3.boot.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * ClassName: APIController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-08-28 10:57
 * @Version 1.0
 */
@RestController
public class APIController {

    @Autowired
    private JuheCnProperties juheCnProperties;

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private MobileService mobileService;

    @Autowired
    private WeightService weightService;

    @Autowired
    private ConstellationService constellationService;

    /**
     * 查询天气预报信息
     * @param area 指定某个城市
     * @return
     */
    @GetMapping("/weather/{area}")
    public Mono<String> weather(@PathVariable("area") String area){
        Mono<String> weatherMono = weatherService.weatherInterface(area);

        return weatherMono;
    }

    /**
     * 查询手机归属地信息
     * @param mobile 查询某个手机号码
     * @return
     */
    @GetMapping("/phone/belong/{mobile}")
    public Mono<String> phoneBelong(@PathVariable("mobile") String mobile){
        Mono<String> mobileLocation = mobileService.queryMobileLocation(mobile);

        return mobileLocation;
    }

    /**
     * 查询星座的运势
     * @param consName 天平座/狮子府/巨蟹座
     * @param type      today/tomorrow/week
     * @return
     */
    @GetMapping("/constellation/{consName}/{type}")
    public Mono<String> queryConstellation(@PathVariable("consName") String consName,
                                           @PathVariable("type") String type){

        return constellationService.getConstellation(consName, type, juheCnProperties.getConsKey());

    }

    /**
     * 标准体重计算
     * @param sex   性别,1:男 2:女, 默认1
     * @param role  计算标准, 1:中国 2:亚洲 3:国际, 默认1
     * @param height 身高(CM), 支持最多1位小数; 如: 178
     * @param weight 体重(KG), 支持最多1位小数; 如: 67.8
     * @return
     */
    @GetMapping("/weight/{sex}/{role}/{height}/{weight}")
    public Mono<String> countStanderWeight(@PathVariable("sex") Integer sex,
                                           @PathVariable("role") Integer role,
                                           @PathVariable("height") Double height,
                                           @PathVariable("weight") Double weight){
//        height = Math.round(height * 10) / 10.0;
//        weight = Math.round(weight * 10) / 10.0;

        return weightService.getStanderWeight(sex, role, height, weight, juheCnProperties.getWeightKey());
    }

}
