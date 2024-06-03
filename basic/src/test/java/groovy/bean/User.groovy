package groovy.bean

import cn.hutool.core.text.CharSequenceUtil
import groovy.transform.ToString
import groovy.transform.builder.Builder
import org.junit.jupiter.api.Test
import spock.lang.Specification

import java.util.logging.Logger

@ToString
@Builder
@Newify(User)
class User extends Specification {
    private Logger log = Logger.getLogger(User.class.getName());
    String name
    int age
    String sex

    String country = "unknown"

    String getCountry() {
        if (country == null || CharSequenceUtil.equals(country, "unknown")) {
            country = "China"
        }
        return country
    }

    def static printUserInfo(User u) {
        println "name = ${u.name}, age = ${u.age}, sex = ${u.sex}"
    }

    @Test
    void testUserInfo() {
        User user1 = builder().name("zhangsan").age(23).sex("man").build();
        log.info("name = ${user1.name}, age = ${user1.age}, sex = ${user1.sex}")

        User user = new User();

        user.setAge(24);
        user.setName("zhangsan")
        user.setSex("man")
        println "name = ${user.name}, age = ${user.age}, sex = ${user.sex}"

        def u = User()
        u.with {
            name = "zhangsan"
            age = 24
            sex = "man"
        }
        log.info("name = ${u.name}, age = ${u.age}, sex = ${u.sex}")
    }

    def "缺失属性安全访问符号"() {
        given:
        def user = new User(name: "zhangsan", age: 18)
        when:
        def id = user?.id;
        then:
        thrown(MissingPropertyException)
    }

    def "直接访问属性操作符"() {
        given:
        def user = new User(name: "zhangsan", age: 18)
        log.info("用户所处国家:${user.getCountry()}")
        def country = user.country;
        log.info("用户所处国家:${user.country}")
        log.info("用户所处国家:${user.@country}")
        expect:
        user.@country == "China"
    }

    def "使用&符号表述方法引用"() {
        given:
        def u1 = new User(name: "zhangsan", age: 18)
        def u2 = new User(name: "lisi", age: 19)
        def list = [u1, u2]
        list.each {u->u.&printUserInfo}
        list.forEach { this.&printUserInfo }
        when:
        list.forEach { this.&printUserInfo }
        then:
        noExceptionThrown()
    }
}

