package com.example.demo.model

class Scene(val oldScene: Scene? = null
            , genDimension: Int = 10
            , genNumber: Int = 100
            , attributesNumber: Int = 10
            , population: Int = 1000
            , relativeDistance: Double = 0.1
            , newEffectivityChangesPersent: Int = 0) {

    val params = Params(genDimension, genNumber, attributesNumber, population, relativeDistance, newEffectivityChangesPersent)

    val lifeConditions = emptyList<List<Int>>().toMutableList()

    init {

    }

    fun isNeedToRegenerate() = params.isBaseParametersChanged(oldScene?.params)

}