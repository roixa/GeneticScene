package com.example.demo.model


data class Params(val genDimension: Int
                  , val genNumber: Int
                  , val attributesNumber: Int
                  , val population: Int
                  , val maxAge: Int
                  , val maxReproductiveAge: Int
                  , val maxSexes: Int
                  , val relativeDistance: Double
                  , val absoluteStartedDistance: Double
                  , val relativeLowestEffectivity: Double
                  , val newEffectivelyChangesPercent: Int
                  , val newGenChangesPersent: Int) {

    companion object {
        fun getStartedData() = Params(3,
                20,
                10,
                700,
                3,
                3,
                4,
                0.6,
                20.0,
                0.05,
                1,
                1)
    }

}