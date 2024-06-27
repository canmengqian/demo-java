package com.demo.ktl.coroutines

import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlin.test.Test


class RunBlockingTest {
    @Test
    fun run_1() {
        runBlocking {
            this.isActive
        }
    }
}