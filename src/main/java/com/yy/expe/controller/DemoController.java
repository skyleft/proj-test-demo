package com.yy.expe.controller;

import com.yy.expe.bean.Demo;
import com.yy.expe.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Controller层面上往往是对接收外部请求并做出响应的正确性的检查
 * HTTP参数,响应码
 * Controller层面上如果是使用的MVC则可用Spring的mockmvc进行测试
 * 如果是使用基于servlet的技术,如NyyFramework,则可以使用mockito进行测试
 * Created by andy on 15/11/21.
 */
@RestController
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @RequestMapping("/demo1")
    public Object demo1() {
        return "demo1";
    }

    @RequestMapping("/demo2")
    public Object demo2(@RequestParam(value = "p") Integer p) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", p);
        result.put("msg", "code is "+p);
        return result;
    }

    @RequestMapping("/demo3")
    public Object demo3(Demo demo) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Demo newDemo = this.demoService.addOrUpdateDemo(demo);
            result.put("code", "0");
            result.put("data", demo);
            result.put("msg", "success");
        } catch (Throwable t) {
            result.put("code", "1");
            result.put("msg", "failed");
        }
        return result;
    }

    @RequestMapping("/demo4")
    public Object demo4(Demo demo) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "0");
        result.put("data", this.demoService.getAllDemos());
        result.put("msg", "success");
        return result;
    }

    @RequestMapping("/demo5")
    public Object demo5(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if ("cookiename".equalsIgnoreCase(cookie.getName())) {
                cookieValue = cookie.getValue();
                break;
            }
        }
        result.put("cookiename", cookieValue);
        return result;
    }
}
