package com.example.demo.model

import java.util.*

open class Randomly(private val dimension: Int) {

    val rand = Random(System.currentTimeMillis())

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

    fun getNextValue(): Int = values[rand.nextInt(dimension)]

}

fun isTrueRandomly() = Random(System.currentTimeMillis()).nextBoolean()
