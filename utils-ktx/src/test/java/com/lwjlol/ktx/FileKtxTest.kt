package com.lwjlol.ktx

import org.junit.Test

class FileKtxTest {


    @Test
    fun testFileName(){
        println("q/wqeqwe/qweqweqwe/qweqweqwe".fileName)
        println("          ".fileName)
        println("     /     ".fileName)
        println("121313/".fileName)
        println("/".fileName)
        println("qeqwe/qeqe.jpg".fileName)
    }
}