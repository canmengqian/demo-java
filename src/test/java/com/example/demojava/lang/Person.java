package com.example.demojava.lang;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className Person
 * @description TODO
 * @date 2023/8/2
 */
@Data
@ToString
public class Person implements Serializable {
    String name;
    transient String like;


}
