package com.example.demo.aspect.service;

import org.springframework.stereotype.Service;

/**
 * @Description 定义Service层，不需要调用方法，
 * 输出一下即可，验证一下执行顺序。
 * @Auther 葛定财
 * @Date 2019/11/13 9:09
 * @Version 1.0
 */
@Service
public class SysLogService {

    public boolean save() {
        System.out.println("==Daniel Ge logger==: beginService - save ");
        return true;
    }
}
