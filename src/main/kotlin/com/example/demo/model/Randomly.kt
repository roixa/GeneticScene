package com.example.demo.model

import java.util.*

open class Randomly(private val dimension: Int,val rand:Random) {

    val values = IntArray(2 * dimension)

    init {
        var count = -dimension
        var i = 0
        while (i < 2 * dimension) {
            if (count != 0) {
                values[i] = count
                i++
            }
            count++
        }
    }

    fun getNextRandomValue(): Int = values[rand.nextInt(2 * dimension)]

    fun isTrueRandomly() = rand.nextBoolean()
}

