package com.wizz.demo.Annotaions;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//注解的生命周期，一直到程序运转时这个注解都存在
@Target(ElementType.METHOD)//注解的作用目标，方法，接口等
@Inherited
@Documented
public @interface Log {//自定义的log注解
    String logStr() default "";//将日志文件存到这个位置
}
