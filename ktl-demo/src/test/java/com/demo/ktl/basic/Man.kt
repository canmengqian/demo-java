package com.demo.ktl.basic


import org.testng.annotations.Test

class Man : Person() {
    override fun walk() {
        println("man walk")
    }

    fun manWalk() {
        println("man walk")
    }

    @Test
    fun test_person_manWalk() {
        val man: Person = Man()

    }
}


