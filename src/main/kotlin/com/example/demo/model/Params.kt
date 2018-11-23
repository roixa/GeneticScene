package com.example.demo.model


data class Params(val genDimension: Int
                  , val genNumber: Int
                  , val attributesNumber: Int = 10
                  , val population: Int
                  , val relativeDistance: Double
                  , val newEffectivelyChangesPercent: Int) {

}