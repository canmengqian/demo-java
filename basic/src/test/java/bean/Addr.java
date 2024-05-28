package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className Addr
 * @description TODO
 * @date 2023/8/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Addr implements Serializable {
    String location;
}
