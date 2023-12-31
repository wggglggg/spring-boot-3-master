package com.sgg.springboot3.boot.entity;

import lombok.Data;

/**
 * ClassName: EveryoneLocation
 * Description:
 *      卫星定位 实体类，
 * @Author wggglggg
 * @Create 2023-09-06 20:15
 * @Version 1.0
 */
@Data
public class SatellitePosition {

    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;

//    public SatellitePosition() {
//    }
//
//    public SatellitePosition(String item1, String item2, String item3, String item4) {
//        this.item1 = item1;
//        this.item2 = item2;
//        this.item3 = item3;
//        this.item4 = item4;
//    }
//
//    public String getItem1() {
//        return item1;
//    }
//
//    public void setItem1(String item1) {
//        this.item1 = item1;
//    }
//
//    public String getItem2() {
//        return item2;
//    }
//
//    public void setItem2(String item2) {
//        this.item2 = item2;
//    }
//
//    public String getItem3() {
//        return item3;
//    }
//
//    public void setItem3(String item3) {
//        this.item3 = item3;
//    }
//
//    public String getItem4() {
//        return item4;
//    }
//
//    public void setItem4(String item4) {
//        this.item4 = item4;
//    }

    @Override
    public String toString() {
        return item1 +"-地点经纬度为：{" +
               "北纬='" + item2 +  "度" + '\'' +
               " : '" + item3 +  "分" + '\'' +
               ", 东经='" + item4 + "度" + '\'' +
               " : '" + item5 + "分" + '\'' +
               '}';
    }


//    @Override
//    public String toString() {
//        return "SatellitePosition{" +
//               "item1='" + item1 + '\'' +
//               ", item2='" + item2 + '\'' +
//               ", item3='" + item3 + '\'' +
//               ", item4='" + item4 + '\'' +
//               '}';
//    }
}
