package com.example.springwebdemo.demos.web.spring;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ApplicationContextController
 * @description 获取request对象
 * @date 2024/5/21
 */
@RestController
@RequestMapping("/spring/applicationContext")
@Slf4j
public class ApplicationContextController {

    @Resource
    HttpServletRequest httpServletRequest;

    @RequestMapping("/getHttpServletRequest")
    public void getHttpServletRequest(@RequestParam(required = false) String name) {
        log.info("httpServletRequest:{}", httpServletRequest);
        log.info("mapping - servlet :{}", httpServletRequest.getHttpServletMapping().getServletName());
        log.info("mapping {}", JSON.toJSONString(httpServletRequest.getHttpServletMapping()));
        log.info("URI:{}", httpServletRequest.getRequestURI());
        log.info("URL:{}", httpServletRequest.getRequestURL());
        log.info("context path:{}", httpServletRequest.getContextPath());
        log.info("method:{}", httpServletRequest.getMethod());
        log.info("server Name :{},server port:{}", httpServletRequest.getServerName(), httpServletRequest.getServerPort());
    }
}
