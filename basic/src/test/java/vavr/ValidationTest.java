package vavr;


import bean.JaPerson;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ValidationTest
 * @description TODO
 * @date 2023/8/29
 */
public class ValidationTest {
    void test(){
        Validation<Seq<String>, JaPerson> validation = Validation.valid(new JaPerson("lisi"));
        //validation.ap(new Validation.Valid<>());
    }
}
