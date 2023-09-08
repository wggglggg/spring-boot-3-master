package com.sgg.springboot3.boot.contoller;

import com.sgg.springboot3.boot.entity.SatellitePosition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SatellitePosition
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-06 20:36
 * @Version 1.0
 */

@RestController
public class SatellitePositionController {

    @PostMapping("/satellite/position/add")
    public String addSatellitePositionInfo(@RequestParam("position") SatellitePosition info){
        return "接收的设备信息："+info.toString();
    }


}
