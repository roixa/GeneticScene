package com.example.demo.view.common

import com.example.demo.model.Scene
import com.example.demo.view.fields.AttributesField
import com.example.demo.view.fields.DimensionField
import com.example.demo.view.fields.NumberField
import com.example.demo.view.fields.PopulationField

class UISnapshot(scene: Scene, position: Int) {

    companion object {
        fun getParams(scene: Scene, position: Int = 0): List<UIParam> = listOf(
                NumberField(position, scene.params.genNumber),
                DimensionField(position, scene.params.genDimension),
                AttributesField(position, scene.params.attributesNumber),
                PopulationField(position, scene.params.population)
        )
    }

    val params: List<UIParam> = getParams(scene, position)

    fun numberColomn() = (params[0] as IEditableField<*>).editableField
    fun dimensionColomn() = (params[1] as IEditableField<*>).editableField
    fun attributesColomn() = (params[2] as IEditableField<*>).editableField


    fun getEditableParams() = params.filter { it is IEditableField<*> }

    fun getChartPointsByName(name: String) = params
            .filter { it.name == name }
            .filter { it is IChartPoint }
            .map {
                it as IChartPoint
                it.getChartProperty(it.value.toInt())
            }


}