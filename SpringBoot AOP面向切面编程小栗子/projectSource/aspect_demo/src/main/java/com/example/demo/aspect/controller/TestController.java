package com.example.demo.aspect.controller;

import com.example.demo.aspect.annotation.SysLog;
import com.example.demo.aspect.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Auther 葛定财
 * @Date 2019/11/13 9:14
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    private SysLogService sysLogService;

    @SysLog("测试")
    @GetMapping("/test")
    public String test() {
        System.out.println("==Daniel Ge logger==: beginTest======= ");
        sysLogService.save();
        return "over=======";
    }
}
