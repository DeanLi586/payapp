package com.payapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nexus on 12/7/17.
 */
@Controller
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/hellothere")
    @ResponseBody
    public String hiya(@RequestParam String name){
        return "Hello "+name;
    }
}
