package com.example.administrator.epgiskotlin.utils

import android.util.Log

/**
 * Created by liu on 2020/5/20.
 */
class LogUtils {
    companion object {
         fun v(str1: String, str2: String) {
            if (!Constants.isLogshow) return
            Log.v(str1, str2)
        }

         fun i(str1: String, str2: String) {
            if (!Constants.isLogshow) return
            Log.v(str1, str2)
        }

         fun e(str1: String, str2: String) {
            if (!Constants.isLogshow) return
            Log.v(str1, str2)
        }

         fun d(str1: String, str2: String) {
            if (!Constants.isLogshow) return
            Log.v(str1, str2)
        }
    }

}