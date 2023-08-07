package guava.primitive;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CaseFormatTest {
    @Test
    void  test(){
        // 小写驼峰转常量
        Converter<String,String> converter = CaseFormat.LOWER_CAMEL
                .converterTo (CaseFormat.UPPER_UNDERSCORE);
        System.out.println (converter.convert ("lowCamel"));
        List<String> lcs = Lists.newArrayList ();
        lcs.add ("lowCamel");
        lcs.add ("lowCamelCast");
        converter.convertAll (lcs).forEach (System.out::println);
    }
}
