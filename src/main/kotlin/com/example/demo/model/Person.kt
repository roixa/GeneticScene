package com.example.demo.model

import java.util.*
import kotlin.math.pow

open class Person(val genDimension: Int, val genNumber: Int,rand:Random) : Randomly(genDimension,rand) {

    val genes = IntArray(genNumber) { getNextRandomValue() }

    var age = 0

    fun distanceTo(person: Person): Double = genes
            .zip(person.genes)
            .map { it.first.minus(it.second) }
            .map { it * it }
            .sum().toDouble()
            .pow(0.5)


}