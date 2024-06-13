package ktl.dataobj

import org.junit.jupiter.api.Test

data class DtPerson(var name: String, var age: Int) {


}

class DtPersonTest {
    @Test
    fun test_init() {
        var dtPerson = DtPerson("Tom", 18)
        dtPerson.age = 19
        println(dtPerson)
        val pair = dtPerson to "a"
        println(pair)
        println(pair.first)
        println(pair.second)

    }
}
