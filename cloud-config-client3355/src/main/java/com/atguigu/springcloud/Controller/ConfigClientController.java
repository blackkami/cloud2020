package com.atguigu.springcloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")    //这个config.info是在拉取的配置yml里写的
    private String configInfo;


    @GetMapping("/configInfo")
    public String getConfigInfo(){
        //当远程（Git）修改配置文件后，还需要触发POST请求 /actuator/refresh
        return configInfo;
    }

}
