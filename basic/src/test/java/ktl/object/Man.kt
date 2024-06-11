package ktl.`object`

import lombok.Data
import org.junit.jupiter.api.Test

@Data
class Man:Person{
    constructor(){

    }
    constructor(city: String) : super() {
        this.city = city
    }

    constructor(name: String, city: String) : super() {
        this.name = name
        this.city = city
    }

    var name: String = "Tom"
    var city: String = "Beijing"
        // getter and setter
        get() = field
        set(value) {
            field = value
        }

    fun sayHello() {
        println("Hello")
    }

    override fun play() {
        println("男人玩球")
    }

    override fun humanTalking() {
       println("男人只会简单聊天")
    }

    override fun doSomething() {
     println("做体力活")
    }


}