package design.model.filter.demo1;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Person {
    String name;
    int age;

    int sex;

}
