package com.example.demo.model

import com.example.demo.controller.Logger
import java.util.*

class Scene(val oldScene: Scene? = null
            , params: Params) : Randomly(params.genDimension, Random(System.currentTimeMillis())) {


    val genDimension: Int = params.genDimension
    val genNumber: Int = params.genNumber
    val attributesNumber: Int = params.attributesNumber
    val maxAge: Int = params.maxAge
    val maxReproductiveAge: Int = params.maxReproductiveAge
    val maxSexes: Int = params.maxSexes
    val relativeDistance: Double = params.relativeDistance
    val startedMaxDistance: Double
    val relativeLowestEffectivity: Double = params.relativeLowestEffectivity
    val newEffectivityChangesPersent: Int = params.newEffectivelyChangesPercent
    val newGenChangesPercent: Int = params.newGenChangesPersent
    var population: Int = params.population
        private set
    val personsEffectivity: Int
    val clusters: List<List<Person>>
    val maxDistanceInCluster: Double
    val conditions: Array<IntArray>
    val effectivity: IntArray
    val persons: MutableList<Person>


    init {
        if (isNeedToRegenerate() || oldScene == null) {
            conditions = generateNewConditions()
            effectivity = generateNewEffectivities()
            persons = generateNewPopulation()
            startedMaxDistance = calculatesAverageMaxDistance()
        } else {
            conditions = oldScene.conditions
            effectivity = prepareExistingEffectivity()!!
            persons = prepareExistingPersons()!!
            startedMaxDistance = params.absoluteStartedDistance
//            startedMaxDistance = calculatesAverageMaxDistance()

        }
        Logger.logPopulation(persons)
        personsEffectivity = calculateAveragePersonsEffectivity()
        clusters = generateClusters()
        maxDistanceInCluster = calculatesAverageMaxDistanceInClusters(clusters)
        bornNewPopulation()
        destroyOldPopulation()
        population = persons.size

    }


    private fun bornNewPopulation() {
        clusters.forEach { persons ->
            val males = persons.mapNotNull { it as? Male }
            val females = persons.mapNotNull { it as? Female }
            females.sortedByDescending { it.effectivity }
                    .filter { it.age < maxReproductiveAge }
                    .forEach {
                        interactPair(males, it)
                    }
        }
    }

    private fun destroyOldPopulation() {
        persons.removeIf {
            it.age > maxAge || it.effectivity < 0//(personsEffectivity.toDouble() * relativeLowestEffectivity))
        }
    }

    private fun interactPair(males: List<Male>, female: Female) {
        val choosen = males
                .filter { it.sexes < maxSexes }
                .maxBy { it.effectivity }
        if (choosen != null) {
            persons.add(female.sex(choosen, newGenChangesPercent))
            choosen.sexes = choosen.sexes + 1
        }
    }

    private fun generateNewConditions() = Array(attributesNumber) {
        IntArray(genNumber) { getNextRandomValue() }
    }

    private fun generateNewEffectivities() = IntArray(attributesNumber) {
        getNextRandomValue()
    }

    private fun prepareExistingPersons() = oldScene?.persons?.apply {
        forEach {
            it.age = it.age + 1
            it.refreshPersonalEffectivity(conditions, effectivity)
        }

        filter { it is Male }
                .forEach {
                    it as Male
                    it.sexes = 0
                }
    }

    private fun generateNewPopulation() = MutableList(population) {
        if (isTrueRandomly()) {
            Male(genDimension, genNumber, rand).apply {
                this.refreshPersonalEffectivity(conditions, this@Scene.effectivity)
            }
        } else {
            Female(genDimension, genNumber, rand).apply {
                this.refreshPersonalEffectivity(conditions, this@Scene.effectivity)
            }
        }
    }

    private fun prepareExistingEffectivity() = oldScene?.effectivity?.apply {
        forEachIndexed { i, p ->
            if (isTrueRandomly(newEffectivityChangesPersent)) {
                this[i] = getNextRandomValue()
            }
        }
    }

    private fun calculatesAverageMaxDistance(): Double {
        var max = 0.0
        persons.forEach { first ->
            persons.forEach { second ->
                val d = first.distanceTo(second)
                if (d > max) {
                    max = d
                }
//                max += d
            }
        }
//        return max.div(persons.size * persons.size)
        return max
    }

    private fun calculatesAverageMaxDistanceInClusters(clusters: List<List<Person>>): Double {
        val ret = clusters
                .filter { it.isNotEmpty() }
                .sumByDouble { cluster ->
                    var max = 0.0
                    cluster.forEach { person ->
                        cluster.forEach {
                            val d = person.distanceTo(it)
                            max += d
                        }
                    }
                    max.div(cluster.size * cluster.size)
                }
        return ret.div(clusters.size)
    }


    private fun calculateAveragePersonsEffectivity() = persons
            .sumBy { it.effectivity }
            .div(persons.size)


    private fun isNeedToRegenerate() = genDimension != oldScene?.genDimension && genDimension != oldScene?.genNumber

    private fun generateClusters(): List<List<Person>> {
        val clusters = mutableListOf<List<Person>>()
        generateClustersRecursive(persons, clusters)
        return clusters
    }

    private fun generateClustersRecursive(persons: List<Person>, ret: MutableList<List<Person>>) {
        if (persons.isEmpty()) return
        val maxDistance = startedMaxDistance * relativeDistance
        val core = persons.first()
        val cluster = persons.filter { it.distanceTo(core) <= maxDistance }
        val out = persons.filter { it.distanceTo(core) > maxDistance }
        ret.add(cluster)
        generateClustersRecursive(out, ret)
    }

}