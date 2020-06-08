package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_OK,id: " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        int timeNumber = 3;
//        int age = 10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_Timeout，id: " + id + "\t耗时(秒)：" + timeNumber;
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + " 系统繁忙或者运行报错，请稍后再试，id: " + id + "\t o(╥﹏╥)o：";
    }

    //===================下边熔断，上边降级=============================================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                      //是否起开断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),         //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   //跳闸后时间窗口期，过了时间窗口，下一次请求若成功，则关闭断路器，失败则重置断路器
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")        //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id<0){
            throw new RuntimeException("*****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t 调用成功，流水号： "+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍后再试，o(╥﹏╥)o   id: "+id;
    }


}
