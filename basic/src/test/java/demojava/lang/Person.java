package demojava.lang;

import lombok.*;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person implements Serializable {
    String name;

    int age;

    double salary;
    transient String like;


}
