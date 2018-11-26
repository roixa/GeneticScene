package com.example.demo.view

import com.example.demo.model.Params
import com.example.demo.model.Person
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
    val newEffectivityChangesPersent = NewEffectivityChangesPercentField(position, scene.newEffectivityChangesPersent)
    val maxAge = MaxAgeField(position, scene.maxAge)
    val maxReproductiveAge = MaxReproductiveAgeField(position, scene.maxReproductiveAge)
    val maxRelativeDistance = MaxRelativeDistanceField(position, scene.relativeDistance)

    val geneticPoints: List<GeneticPointField> = scene
            .persons
            .map { it.to2DimensionProjection() }
            .map {
                GeneticPointField(it.first, it.second)
            }

    val clusters = generateClusters(scene)

    val paramsList: List<UIParam> = listOf(population, effectivity, distance, maxRelativeDistance, number, dimension, attributes, maxAge, maxReproductiveAge, newEffectivityChangesPersent)

    fun toParams() = Params(dimension.value.toInt()
            , number.value.toInt()
            , attributes.value.toInt()
            , population.value.toInt()
            , maxAge.value.toInt()
            , maxReproductiveAge.value.toInt()
            , relativeDistance = maxRelativeDistance.value.toDouble()
            , newEffectivelyChangesPercent = newEffectivityChangesPersent.value.toInt())

    fun generateClusters(scene: Scene): List<Pair<String, Int>> {
        val clusters = MutableList(0) { emptyList<Person>().toMutableList() }
        val unsorted = MutableList(scene.persons.size) { scene.persons[it] }
        while (unsorted.size != 0) {
            val new = generateCluster(unsorted, unsorted.first(), scene.maxDistance)
            clusters.add(new)
        }
        return clusters.mapIndexed { index, mutableList ->
            Pair(index.toString(), mutableList.size)
        }

    }

    fun generateCluster(persons: MutableList<Person>, example: Person, maxDistance: Double): MutableList<Person> {
        val new = persons.filter { it.distanceTo(example) <= maxDistance }
        persons.removeAll(new)
        return new.toMutableList()
    }

}