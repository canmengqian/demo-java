package bean;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className RecordPerson
 * @description TODO
 * @date 2023/8/31
 */
@EqualsAndHashCode
@ToString
public final class RecordPerson {
    private final String name;

    public RecordPerson(String name) {
        this.name = name;
    }

}
