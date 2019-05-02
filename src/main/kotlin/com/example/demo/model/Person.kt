package com.example.demo.model

import java.util.*
import kotlin.math.pow

open class Person(val genDimension: Int, val genNumber: Int, rand: Random) : Randomly(genDimension, rand) {

    val genes = IntArray(genNumber) { getNextRandomValue() }

    var age = 0

    fun distanceTo(person: Person): Double = distanceTo(this.genes, person.genes)

    fun to2DimensionProjection(): Pair<Double, Double> {
        val first = IntArray(genNumber) { genDimension }
        val second = IntArray(genNumber) { 0 }
        val x = distanceTo(first, genes)
        val y = distanceTo(second, genes)
        return Pair(x, y)
    }

    private fun distanceTo(first: IntArray, second: IntArray) = first
            .zip(second)
            .map { it.first.minus(it.second) }
            .map { it * it }
            .sum().toDouble()
            .pow(0.5)

}