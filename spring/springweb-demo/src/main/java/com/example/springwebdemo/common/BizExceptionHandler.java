package com.example.springwebdemo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className BizExceptionHandler
 * @description TODO
 * @date 2024/5/21
 */
@RestControllerAdvice
@Slf4j
public class BizExceptionHandler {
    /**
     * 处理自定义异常: 参数校验validation框架支持
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String handleException(MethodArgumentNotValidException e) {
        e.getFieldErrors().forEach(fr -> {
            log.info("字段名称:{},入参信息:{},错误消息:{}", fr.getField(), fr.getArguments(), fr.getDefaultMessage());
        });
        log.error("系统异常:{}", e);
        return "系统异常";
    }
}
