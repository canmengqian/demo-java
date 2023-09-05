package com.example.demojava.keyword.sealed;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className Squrad
 * @description TODO
 * @date 2023/9/5
 */
@Data
@AllArgsConstructor
public final class Squrad implements Shape {
    double length;
    double width;

    @Override
    public double area() {
        return this.width * this.length;
    }
}
