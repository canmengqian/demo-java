package groovy.bean

import groovy.transform.ToString
import groovy.transform.builder.Builder
import org.junit.jupiter.api.Test

import java.util.logging.Logger

@ToString
@Builder
class User {
    private Logger log = Logger.getLogger(User.class.getName());
    String name
    int age
    String sex


    @Test
    void testUserInfo() {
        User user1 = builder().name("zhangsan").age(23).sex("man").build();
        log.info("name = ${user1.name}, age = ${user1.age}, sex = ${user1.sex}")

        User user = new User();

        user.setAge(24);
        user.setName("zhangsan")
        user.setSex("man")
        println "name = ${user.name}, age = ${user.age}, sex = ${user.sex}"
    }
}
