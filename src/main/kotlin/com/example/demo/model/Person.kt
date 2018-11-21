package com.example.demo.model

open class Person(val genDimension: Int, val genNumber: Int) : Randomly(genDimension) {

    val genes = IntArray(genNumber) { getNextRandomValue() }

    var age = 0

    fun distanceTo(person: Person): Int = genes
            .zip(person.genes)
            .map { it.first.minus(it.second) }
            .map { it * it }
            .sum()

}