package com.example.demo.model


data class Params(val genDimension: Int = 10
                  , val genNumber: Int = 10
                  , val attributesNumber: Int = 10
                  , val population: Int = 200
                  , val maxAge: Int = 3
                  , val maxReproductiveAge: Int = 2
                  , val maxSexes: Int = 3
                  , val relativeDistance: Double = 0.5
                  , val newEffectivelyChangesPercent: Int = 0) {

}