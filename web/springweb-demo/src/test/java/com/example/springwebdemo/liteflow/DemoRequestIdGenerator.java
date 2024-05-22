package com.example.springwebdemo.liteflow;

import cn.hutool.core.lang.Snowflake;
import com.yomahub.liteflow.flow.id.RequestIdGenerator;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className DemoRequestIdGenerator
 * @description TODO
 * @date 2024/5/22
 */
public class DemoRequestIdGenerator implements RequestIdGenerator {
    private final static Snowflake snowflake = new Snowflake(1);

    @Override
    public String generate() {
        return snowflake.nextIdStr();
    }
}
