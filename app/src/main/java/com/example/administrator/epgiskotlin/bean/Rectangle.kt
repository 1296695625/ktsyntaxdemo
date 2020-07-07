package com.example.administrator.epgiskotlin.bean

/**
 * Created by liu on 2020/6/3.
 */
class Rectangle(round: Double) : Shape(round) {

    fun length():Double {return round}

    override fun radius() {
        super.radius()
        round++;
    }
}