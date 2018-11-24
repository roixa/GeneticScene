package com.example.demo.view

import com.example.demo.model.Params
import com.example.demo.model.Scene
import com.example.demo.view.common.UIParam
import com.example.demo.view.fields.*

class UIParams(scene: Scene, position: Int = 0) {
    val number = NumberField(position, scene.genNumber)
    val dimension = DimensionField(position, scene.genDimension)
    val attributes = AttributesField(position, scene.attributesNumber)
    val population = PopulationField(position, scene.population)
    val effectivity = EffectivityField(position, scene.personsEffectivity.toDouble())
    val distance = DistanceField(position, scene.maxDistance)

    val geneticPoints: List<GeneticPointField> = scene
            .persons
            .map { it.to2DimensionProjection() }
            .map {
                GeneticPointField(it.first, it.second)
            }

    val paramsList: List<UIParam> = listOf(number, dimension, attributes, population, effectivity, distance)

    fun toParams() = Params(dimension.value.toInt()
            , number.value.toInt()
            , attributes.value.toInt()
            , population.value.toInt())

}