package com.example.demo.view.common

import com.example.demo.model.Scene
import com.example.demo.view.fields.*

class UISnapshot(scene: Scene, position: Int) {

    companion object {
        fun getParams(scene: Scene, position: Int = 0): List<UIParam> = listOf(
                NumberField(position, scene.params.genNumber),
                DimensionField(position, scene.params.genDimension),
                AttributesField(position, scene.params.attributesNumber),
                PopulationField(position, scene.population),
                EffectivityField(position, scene.personsEffectivity.toDouble()),
                DistanceField(position, scene.maxDistance)

        )
    }

    val params: List<UIParam> = getParams(scene, position)

    fun numberColomn() = (params[0] as IEditableField<*>).editableField
    fun dimensionColomn() = (params[1] as IEditableField<*>).editableField
    fun attributesColomn() = (params[2] as IEditableField<*>).editableField
    fun populationColomn() = (params[3] as IEditableField<*>).editableField

    fun effecrivityColomn() = (params[4] as IEditableField<*>).editableField

    fun distanceColomn() = (params[5] as IEditableField<*>).editableField

    fun getEditableParams() = params.filter { it is IEditableField<*> }

    fun getChartPointsByName(name: String) = params
            .filter { it.name == name }
            .filter { it is IChartPoint }
            .map {
                it as IChartPoint
                it.getChartProperty(it.value.toInt())
            }


}