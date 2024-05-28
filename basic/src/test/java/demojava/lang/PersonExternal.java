package demojava.lang;

import lombok.Data;
import lombok.ToString;

import java.io.*;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className Person
 * @description TODO
 * @date 2023/8/2
 */
@Data
@ToString
public class PersonExternal implements Externalizable {
    String name;
    transient String like;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
