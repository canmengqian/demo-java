package com.example.demojava.lang.constant;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.constant.ConstantDescs;
import java.lang.constant.DynamicCallSiteDesc;
import java.lang.constant.DynamicConstantDesc;
import java.lang.constant.MethodHandleDesc;
import java.lang.invoke.MethodHandle;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ConstantDescsTest
 * @description TODO
 * @date 2023/12/14
 */
@Slf4j
public class ConstantDescsTest {
    @Test
    void testDesc() {
        var cd = ConstantDescs.CD_Boolean;
        log.info(ConstantDescs.CD_Boolean.toString());
        log.info(cd.descriptorString());
        log.info(cd.displayName());
        log.info(cd.packageName());
        log.info(cd.arrayType().toString());
        log.info(cd.isArray() + "");
        log.info(cd.isPrimitive() + "");
        log.info(cd.isClassOrInterface() + "");
    }

}
