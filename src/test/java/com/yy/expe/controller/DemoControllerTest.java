package com.yy.expe.controller;

import com.yy.expe.BaseCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by andy on 15/11/22.
 */
public class DemoControllerTest extends BaseCase {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //对于普通的xml配置的基于webapplicationContext的容器可以直接这样构造mockmvc
        //this.mockMvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) this.applicationContext).build();
        //annoation配置的可以指定controller来初始化
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.applicationContext.getBean("demoController")).build();
    }

    /**
     * Method: demo1()
     */
    @Test
    public void testDemo1() throws Exception {
        /*
        对于返回视图的controller简单的测试返回码200,以及正确的视图即可
         */
        mockMvc.perform(get("/demo1"))
                .andExpect(status().isOk())
                .andExpect(content().string("demo1"))
                .andDo(print()).andReturn();
    }

    /**
     * Method: demo2(@RequestParam(value = "p") Integer p)
     */
    @Test
    public void testDemo2() throws Exception {
        /*
        rest风格的controller直接测试返回的json
         */
        mockMvc.perform(get("/demo2").param("p","1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andDo(print()).andReturn();
    }

    /**
     * Method: demo3(Demo demo)
     */
    @Test
    @Ignore
    public void testDemo3() throws Exception {
    }

    /**
     * Method: demo4(Demo demo)
     */
    @Test
    @Ignore
    public void testDemo4() throws Exception {
    }

    /**
     * Method: demo5
     */
    @Test
    public void testDemo5() throws Exception{
        Cookie cookie = new Cookie("cookiename","cn");
        this.mockMvc.perform(get("/demo5").cookie(cookie).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cookiename").value("cn"))
                .andDo(print())
                .andReturn();

    }


}
