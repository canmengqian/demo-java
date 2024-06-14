package ktl.obj

class Woman : Person() {
    override fun play() {
        println("女人织布")
    }

    override fun humanTalking() {
        println("女人聊天聊个不停")
    }

    override fun doSomething() {
        println("女人做手工活")
    }
}