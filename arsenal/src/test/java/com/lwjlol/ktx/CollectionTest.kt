package com.lwjlol.ktx

import org.junit.Test

class CollectionTest {


    @Test
    fun testModify() {
        val list = mutableListOf<Int>()
        repeat(100) {
            list.add(it)
        }
        val modify = list.replace(predicate = { it > 20 }) {
            it-20
        }
        val modifyF = list.replaceFirst(predicate = { it > 20 }) {
            -1
        }

        val modifyL = list.replaceLast(predicate = { it > 20 }) {
            -1
        }

        println(modify)
        println(modifyF)
        println(modifyL)

    }
}