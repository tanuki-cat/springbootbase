package com.lichi.springbootbase.controller.test;

import com.lichi.springbootbase.annotations.WebLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试jwt鉴权
 * @author: lychee
 * @Date: 2022/12/20 13:16
 * @Version: 1.0
 * @Since: 2022/12/20
 */
@RestController
@RequestMapping("/api/test")
public class TestAuthController {

    @WebLog(description = "测试鉴权")
    @GetMapping("/test01")
    public String test01() {
        return "test";
    }
}
