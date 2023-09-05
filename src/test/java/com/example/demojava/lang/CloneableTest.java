package com.example.demojava.lang;

import cn.hutool.core.bean.BeanUtil;
import domain.bean.Addr;
import domain.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.SerializationUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className CloneableTest
 * @description TODO
 * @date 2023/8/31
 */
@Slf4j
public class CloneableTest {
    @Test
    void test() {
        Person person = new Person();
        person.setName("张三");
        person.setAddr(new Addr("杭州"));
        person.setSalary(new BigDecimal(10));
        person.setBirthday(new Date());
        Map<String, String> extProps = new LinkedHashMap<>();
        extProps.put("pet", "tom");
        person.setExtProps(extProps);
        log.info(person.toString());
        byte[] bs = SerializationUtils.serialize(person);
        Person p2 = (Person) SerializationUtils.deserialize(bs);
        log.info("serialize  p1:{},p2:{}", person.toString(), p2.toString());
        p2.getAddr().setLocation("南京");
        p2.setSalary(p2.getSalary().add(BigDecimal.valueOf(10)));
        extProps.put("pet", "jerry");
        p2.setExtProps(extProps);
        log.info("serialize seconds  p1:{},p2:{}", person.toString(), p2.toString());
        person.setAddr(new Addr("北京"));
        BeanUtil.copyProperties(person, p2, false);
        log.info("bean utils p1:{},p2:{}", person.toString(), p2.toString());
        p2.setName("lisi");
        p2.getAddr().setLocation("上海");
        log.info("bean utils p1:{},p2:{}", person.toString(), p2.toString());


    }
}
