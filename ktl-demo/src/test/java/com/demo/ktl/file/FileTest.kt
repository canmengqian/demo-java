package com.demo.ktl.file


import java.io.File
import kotlin.test.Test

/**
 * 文件读写
 */
class FileTest {
    @Test
    fun test_read_file() {
        val file = File("D:\\test.txt")
        if (file.exists()) {
            file.isFile
        } else {
            println("文件不存在")
        }
        println(file)
    }
}