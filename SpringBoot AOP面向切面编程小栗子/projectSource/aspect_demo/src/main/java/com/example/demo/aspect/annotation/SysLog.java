package com.example.demo.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Description 自定义一个注解
* @Param
* @Return
* @Auther 葛定财
* @Date 2019/11/12 17:55
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {

    /**
    * @Description 任何场景下的通用日志打印
    */
    String value() default "";
}
