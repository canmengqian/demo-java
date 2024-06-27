package com.demo.ktl.pkg


import kotlin.test.Test
import com.demo.ktl.basic.Animal as ani

class PackageTest {
    @Test
    fun test() {
        // 引包使用了别名,可以使用简称代替类名
        val animal = ani()
        println(animal.getNameByType("cat"))
    }
}