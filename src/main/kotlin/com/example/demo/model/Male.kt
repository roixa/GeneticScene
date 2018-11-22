package com.example.demo.model

import java.util.*

class Male(genDimension: Int, genNumber: Int, rand: Random) : Person(genDimension, genNumber, rand) {
    var sexes = 0

    fun getPersonalEffectivity(conditions: Array<IntArray>, effectivity: IntArray): Int {
        val attributes = conditions.map {
            it.zip(genes).sumBy { it.first * it.second }
        }
        return effectivity.zip(attributes).sumBy { it.first * it.second }
    }
}