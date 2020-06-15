package com.atguigu.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "---------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "---------testB";
    }


    @GetMapping("/testD")
    public String testD(){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("testD 测试RT");
        int age = 10/0;
        log.info("testD 测试异常比例");
        return "---------testD";
    }


    @GetMapping("/testE")
    public String testE(){
        int age = 10/0;
        log.info("testE 测试异常数");
        return "---------testE";
    }

}
