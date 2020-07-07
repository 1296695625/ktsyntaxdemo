package com.example.administrator.epgiskotlin

import android.util.Log

 class Test {

    companion object {
        private var str1: Int = 0;
        private var str2: Int = 0;
        @JvmStatic
        fun main(args: Array<String>) {
            System.out.println("${str1++}")
            System.out.println("${++str2}")
            System.out.println("$str1")
            System.out.println("$str2")
            System.out.println("${1 + ++str1}")
            System.out.println("${1 + str2++}")
        }
    }
}