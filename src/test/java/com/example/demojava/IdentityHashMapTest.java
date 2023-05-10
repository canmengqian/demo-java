package com.example.demojava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className IdentityHashMapTest
 * @description TODO
 * @date 2023/5/10
 */
public class IdentityHashMapTest {
    @Test
    public void testSameKey() {
        IdentityHashMap<demo, String> map = new IdentityHashMap<>();
        demo d1 = new demo("1");
        demo d2 = new demo("1");
        demo d3 = new demo("1");
        demo d4 = new demo("1");
        //map.put(d, "4");

        map.put(d1, "1");
        map.put(d2, "2");
        map.put(d3, "3");
        map.put(d4, "4");

        //System.out.println(System.identityHashCode());

        System.out.println(map.size());
        System.out.println(map.get(d1));
        System.out.println(map.get(new demo("1")));
    }

    @Test
    public void testHashMap() {
        Map<String, String> m = new HashMap<>();
        m.put("1", "1");
        m.put("1", "2");
        m.put("1", "3");
        m.put("1", "4");
        System.out.println(m.size());
        System.out.println(m.get("1"));
        System.out.println(m.get("2"));
    }

    @Test
    public void testDemoHashMap() {
        Object o;
        Map<demo, String> m = new HashMap<>();
        String n = "1";
        String n2 = "1";
        System.out.println(n == n2);
        System.out.println(n.equals(n2));
        System.out.println(n.hashCode() == n2.hashCode());

        demo d1 = new demo("1");
        demo d2 = new demo("1");
        System.out.println((d1.id==d2.id) +"kk");
        System.out.println((d1==d2) +"kk1");
        System.out.println(System.identityHashCode(d1)==System.identityHashCode(d2));
        System.out.println(d1.equals(d2));
        System.out.println(d1.hashCode() == d2.hashCode());

        m.put(new demo("1"), "1");
        m.put(new demo("2"), "2");
        m.put(new demo("3"), "3");
        m.put(new demo("4"), "4");
        m.put(new demo("1"), "5");

        System.out.println(m.size());
        System.out.println(m.get(new demo("1")));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class demo {
        private String id;

        /*@Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            demo demo = (demo) o;
            return Objects.equals(id, demo.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }*/
    }
}
