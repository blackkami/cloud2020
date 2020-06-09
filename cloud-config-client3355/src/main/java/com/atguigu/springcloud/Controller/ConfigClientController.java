package com.atguigu.springcloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

    @Value("${config.info}")    //这个config.info是在拉取的配置yml里写的
    private String configInfo;


    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }

}
