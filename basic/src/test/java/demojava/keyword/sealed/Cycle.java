package demojava.keyword.sealed;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className Cycle
 * @description TODO
 * @date 2023/9/5
 */
@Data
@AllArgsConstructor
public final class Cycle implements Shape {
    double radius;

    @Override
    public double area() {
        return Math.PI * StrictMath.pow(radius, 2);
    }
}
