package com.example.demo.model

import javafx.beans.property.SimpleIntegerProperty


data class Params(val genDimension: Int
                  , val genNumber: Int
                  , val population: Int
                  , val relativeDistance: Double
                  , val newEffectivelyChangesPercent: Int) {
}