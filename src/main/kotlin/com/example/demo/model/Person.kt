package com.example.demo.model

import java.util.*
import kotlin.math.pow

open class Person(val genDimension: Int, val genNumber: Int, rand: Random) : Randomly(genDimension, rand) {

    val genes = IntArray(genNumber) { getNextRandomValue() }

    var age = 0

    fun distanceTo(person: Person): Double = genes
            .zip(person.genes)
            .map { it.first.minus(it.second) }
            .map { it * it }
            .sum().toDouble()
            .pow(0.5)


    fun to2DimensionProjection(): Pair<Double, Double> {
        val half = genNumber / 2
        val x = genes.toList().subList(0, half).sum().toDouble() / half
        val y = genes.toList().subList(half + 1, genes.size - 1).sum().toDouble() / half
        return Pair(x, y)
    }
}