package com.example.demo.model

import java.util.*

class Male(genDimension: Int, genNumber: Int, rand: Random) : Person(genDimension, genNumber, rand) {

    var sexes = 0

    var effectivity: Int = 0
        private set


    fun refreshPersonalEffectivity(conditions: Array<IntArray>, effectivities: IntArray) {
        val attributes = conditions.map {
            it.zip(genes).sumBy { it.first * it.second }
        }
        effectivity = effectivities.zip(attributes).sumBy { it.first * it.second }
    }
}