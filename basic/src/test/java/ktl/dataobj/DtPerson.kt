package ktl.dataobj

import com.alibaba.fastjson.JSON
import lombok.ToString
import org.junit.jupiter.api.Test

data class DtPerson(
    var name: String,
    var age: Int = 0,
    var address: String = "Beijing",
    var salary: Double = 0.0

) {
}

@ToString
class DtPerson_1 {
    var name: String = ""
    var age: Int = 0
}

class DtPersonTest {
    @Test
    fun test_init() {
        var dtPerson = DtPerson("Tom")
        dtPerson.age = 19
        println(dtPerson)
        val pair = dtPerson to "a"
        println(pair)
        println(pair.first)
        println(pair.second)

    }

    @Test
    fun test_dataclass_fastjson() {
        val json = """{"name":"Tom","age":18}"""
        val dtPerson: DtPerson = JSON.parseObject(json, DtPerson::class.java)
        println(dtPerson.toString())
    }

    @Test
    fun test_json() {
        val json = """{"name":"Tom","age":18}"""
        val dtPerson = JSON.parseObject(json, DtPerson_1::class.java)
        println(dtPerson.toString())
    }
}
