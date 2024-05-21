package com.example.springwebdemo.bean.valid;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ValidUser
 * @description 分组校验
 * @date 2024/5/21
 */
@Data
public class ValidUser {
    @NotBlank(message = "名字不能为空", groups = {Group.Default.class})
    @NotEmpty(message = "名字不能为空", groups = {Group.Default.class})
    @NotNull
    String name;

    @NotBlank(message = "ID不能为空", groups = {Group.Delete.class, Group.Update.class})
    String id;
    @Max(value = 150, message = "年龄不能超过150", groups = {Group.Add.class, Group.Update.class})
    @Min(value = 0, message = "年龄不能小于0", groups = {Group.Add.class, Group.Update.class})
    int age;



}
