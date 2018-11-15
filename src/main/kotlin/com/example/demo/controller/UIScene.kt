package com.example.demo.controller

import com.example.demo.model.Scene
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class UIScene(scene: Scene = Scene()) :ViewModel(){

    val genDimensionProperty = SimpleIntegerProperty(this, "Dimension", scene.params.genDimension)
    var genDimension by genDimensionProperty

    val genNumberProperty = SimpleIntegerProperty(this, "Number", scene.params.genNumber)
    var genNumber by genNumberProperty

    val populationProperty = SimpleIntegerProperty(this, "Population", scene.params.population)
    var population by populationProperty

    val relativeDistanceProperty = SimpleDoubleProperty(this, "RelativeDistance", scene.params.relativeDistance)
    var relativeDistance by relativeDistanceProperty

    val newEffectivelyChangesPercentProperty = SimpleIntegerProperty(this, "NewEffectivelyChangesPercent", scene.params.newEffectivelyChangesPercent)
    val newEffectivelyChangesPercent by newEffectivelyChangesPercentProperty

    fun parse(): Scene = Scene(null, genDimension, genNumber, population, relativeDistance, newEffectivelyChangesPercent)

}