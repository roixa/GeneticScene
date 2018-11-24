package com.example.demo.view

import com.example.demo.model.Scene
import com.example.demo.view.common.IChartPoint
import com.example.demo.view.common.IEditableField

class UISnapshot(scene: Scene, position: Int) {

    val params: UIParams = UIParams(scene, position)

    fun numberColomn() = params.number.editableField
    fun dimensionColomn() = params.dimension.editableField
    fun attributesColomn() = params.attributes.editableField
    fun populationColomn() = params.population.editableField
    fun effecrivityColomn() = params.effectivity.editableField
    fun distanceColomn() = params.distance.editableField

    fun getEditableParams() = params.paramsList.filter { it is IEditableField<*> }

    fun getChartPointsByName(name: String) = params.paramsList
            .filter { it.name == name }
            .filter { it is IChartPoint }
            .map {
                it as IChartPoint
                it.getChartProperty(it.value.toInt())
            }



}