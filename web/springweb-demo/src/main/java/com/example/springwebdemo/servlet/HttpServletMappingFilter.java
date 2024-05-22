package com.example.springwebdemo.servlet;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*")
@Slf4j
public class HttpServletMappingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =
                (HttpServletRequest) servletRequest;
       HttpServletMapping mapping = request.getHttpServletMapping ();
       log.info("mapping:{}", JSON.toJSONString (mapping));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
