package domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className Person
 * @description TODO
 * @date 2023/8/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person implements Cloneable, Serializable {
    String name;
    Addr addr;
    BigDecimal salary;
    Date birthday;
    Map<String,String> extProps;
    public Person(String name) {
        this.name = name;
    }
}
