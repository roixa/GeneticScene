package com.example.demo.model


data class Params(val genDimension: Int = 20
                  , val genNumber: Int = 20
                  , val attributesNumber: Int = 10
                  , val population: Int = 500
                  , val maxAge: Int = 3
                  , val maxReproductiveAge: Int = 2
                  , val maxSexes: Int = 3
                  , val relativeDistance: Double = 0.9
                  , val newEffectivelyChangesPercent: Int = 2
                  , val newGenChangesPersent: Int = 10) {

}