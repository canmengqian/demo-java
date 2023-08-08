package com.example.demojava.lang;

import cn.hutool.core.io.IoUtil;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className SerializableTest
 * @description TODO
 * @date 2023/8/2
 */
public class SerializableTest {
    @Test
    void seria() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream(new File("d:\\seria.txt"));
        FileInputStream fis = new FileInputStream(new File("d:\\seria.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Person p = new Person();
        p.setLike("eat");
        p.setName("张三");
        // 序列化对象
        oos.writeObject(p);
        oos.flush();
        oos.close();
        System.out.println("序列化前对象:" + p.toString());
        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(fis);
        p = (Person) ois.readObject();
        System.out.println("序列化后对象:" + p.toString());

    }


}
