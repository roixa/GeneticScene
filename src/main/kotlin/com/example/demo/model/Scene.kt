package com.example.demo.model

class Scene(val oldScene: Scene? = null
            , genDimension: Int = 10
            , genNumber: Int = 100
            , population: Int = 1000
            , relativeDistance: Double = 0.1
            , newEffectivityChangesPersent: Int = 0) {
    val params = Params(genDimension, genNumber, population, relativeDistance, newEffectivityChangesPersent)


}