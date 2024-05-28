package com.example.springwebdemo.demos.web;

import com.example.springwebdemo.bean.valid.Group;
import com.example.springwebdemo.bean.valid.ValidUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ValidateController
 * @description validate框架分组使用
 * @date 2024/5/21
 */
@RequestMapping("/validate/user")
@RestController
@Slf4j
public class ValidateController {
    @PostMapping("/add")
    public String add(@RequestBody @Validated({Group.Add.class, Group.Default.class}) ValidUser user) {
        log.info("add user:{}", user.toString());
        return "add";
    }

    @PostMapping("/update")
    public String update(@RequestBody @Validated(Group.Update.class) ValidUser user) {
        return "update";
    }

    @PostMapping("/del")
    public String del(@RequestBody @Validated(Group.Delete.class) ValidUser user) {
        return "del";
    }

    @PostMapping("/query")
    public String query(@RequestBody @Validated ValidUser user) {
        return "query";
    }
}
