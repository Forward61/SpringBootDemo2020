package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author freedom
 * @Description
 * @Date $ 2020/11/17 12:34
 */
@RestController
public class RestfulClass {

    @RequestMapping("/hello")
    public String showHello(){
        return "hello2";
    }
}
