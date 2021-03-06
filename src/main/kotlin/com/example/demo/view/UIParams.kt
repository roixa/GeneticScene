package com.example.demo.view

import com.example.demo.model.Params
import com.example.demo.model.Scene
import com.example.demo.view.common.UIParam
import com.example.demo.view.fields.*

class UIParams(val scene: Scene, position: Int = 0) {

    val number = NumberField(position, scene.genNumber)
    val dimension = DimensionField(position, scene.genDimension)
    val attributes = AttributesField(position, scene.attributesNumber)
    val population = PopulationField(position, scene.population)
    val effectivity = EffectivityField(position, scene.personsEffectivity.toDouble())
    val distance = DistanceField(position, scene.maxDistanceInCluster)
    val newEffectivityChangesPersent = NewEffectivityChangesPercentField(position, scene.newEffectivityChangesPersent)
    val maxAge = MaxAgeField(position, scene.maxAge)
    val maxReproductiveAge = MaxReproductiveAgeField(position, scene.maxReproductiveAge)
    val maxRelativeDistance = MaxRelativeDistanceField(position, scene.relativeDistance)
    val relativeLowestEffectivityField = RelativeLowestEffectivityField(position, scene.relativeLowestEffectivity)

    val geneticPoints: List<GeneticPointField> = scene
            .persons
            .map { it.to2DimensionProjection() }
            .map {
                GeneticPointField(it.first, it.second)
            }

    val clusters = scene.clusters.mapIndexed { index, list ->
        Pair(index.toString(), list.size)
    }

    val paramsList: List<UIParam> = listOf(population,
            effectivity,
            distance,
            maxRelativeDistance,
            number,
            dimension,
            attributes,
            maxAge,
            maxReproductiveAge,
            relativeLowestEffectivityField,
            newEffectivityChangesPersent)

    fun toParams() = Params(dimension.value.toInt()
            , number.value.toInt()
            , attributes.value.toInt()
            , population.value.toInt()
            , maxAge.value.toInt()
            , maxReproductiveAge.value.toInt()
            , scene.maxSexes
            , maxRelativeDistance.value.toDouble()
            , scene.startedMaxDistance
            , relativeLowestEffectivityField.value.toDouble()
            , newEffectivityChangesPersent.value.toInt()
            , scene.newGenChangesPercent)

}