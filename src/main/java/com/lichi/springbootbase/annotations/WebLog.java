package com.lichi.springbootbase.annotations;

import java.lang.annotation.*;

/**
 * @Description: 请求日志打印
 * @author: lychee
 * @Date: 2022/12/21 10:10
 * @Version: 1.0
 * @Since: 2022/12/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {

        /**
        * 日志描述信息
        * @return string
        */
        String description() default "";

}
