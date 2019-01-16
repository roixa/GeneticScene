package com.example.demo.model

import com.example.demo.controller.Logger
import java.util.*

class Scene(val oldScene: Scene? = null
            , params: Params) : Randomly(params.genDimension, Random(System.currentTimeMillis())) {


    val genDimension: Int = params.genDimension
    val genNumber: Int = params.genNumber
    val attributesNumber: Int = params.attributesNumber
    var population: Int = params.population
    val maxAge: Int = params.maxAge
    val maxReproductiveAge: Int = params.maxReproductiveAge
    val maxSexes: Int = params.maxSexes
    val relativeDistance: Double = params.relativeDistance
    val newEffectivityChangesPersent: Int = params.newEffectivelyChangesPercent
    val newGenChangesPercent: Int = params.newGenChangesPersent
    val personsEffectivity: Int
    val maxDistance: Double

    val conditions: Array<IntArray>
    val effectivity: IntArray
    val persons: MutableList<Person>


    init {
        if (isNeedToRegenerate() || oldScene == null) {
            conditions = Array(attributesNumber) {
                IntArray(genNumber) { getNextRandomValue() }
            }
            effectivity = IntArray(attributesNumber) {
                getNextRandomValue()
            }
            persons = MutableList(population) {
                if (isTrueRandomly()) {
                    Male(genDimension, genNumber, rand)
                } else {
                    Female(genDimension, genNumber, rand)
                }
            }
            maxDistance = calculatesAverageMaxDistance()
        } else {
            conditions = oldScene.conditions
            effectivity = oldScene.effectivity.apply {
                forEachIndexed { i, p ->
                    if (isTrueRandomly(newEffectivityChangesPersent)) {
                        this[i] = getNextRandomValue()
                    }
                }
            }
            persons = oldScene.persons.apply {
                forEach {
                    it.age = it.age + 1
                }
                filter { it is Male }
                        .forEach {
                            it as Male
                            it.sexes = 0
                        }
            }
            maxDistance = calculatesAverageMaxDistance()
            Logger.logPopulation(persons)
            bornNewPopulation()
            destroyOldPopulation()
            population = persons.size

        }

        personsEffectivity = persons.filter { it is Male }
                .map { it as Male }
                .sumBy { it.getPersonalEffectivity(conditions, effectivity) }.div(persons.size)
    }

    private fun bornNewPopulation() {

        val males = persons.filter { it is Male }
                .map { it as Male }
                .sortedByDescending { it.getPersonalEffectivity(conditions, effectivity) }

        persons
                .filter { (it is Female) && (it.age <= maxReproductiveAge) }
                .forEach {
                    it as Female
                    interactPair(males, it, maxDistance)
                }
        val clusters = emptyList<List<Person>>().toMutableList()
        generateClusters(persons, clusters)
        print("clusters.size $clusters.size")
    }

    private fun destroyOldPopulation() {
        persons.removeIf {
            it.age > maxAge
        }
    }

    private fun interactPair(males: List<Male>, female: Female, maxAllowedDistance: Double) {
        val choosen = males
                .filter { it.distanceTo(female) <= maxAllowedDistance }
                .firstOrNull { it.sexes < maxSexes }
        if (choosen != null) {
            persons.add(female.sex(choosen, newGenChangesPercent))
            val sexes = choosen.sexes
            choosen.sexes = sexes + 1

        }
    }

    private fun calculatesAverageMaxDistance(): Double {
        var max = 0.0
        persons.forEach { first ->
            persons.forEach { second ->
                val d = first.distanceTo(second)
                max += d
            }
        }
        return max.times(relativeDistance).div(persons.size * persons.size)
    }


    private fun isNeedToRegenerate() = genDimension != oldScene?.genDimension && genDimension != oldScene?.genNumber

    private fun generateClusters(persons: List<Person>, ret: MutableList<List<Person>>) {
        if (persons.isEmpty()) return
        val core = persons.first()
        val cluster = persons.filter { it.distanceTo(core) <= maxDistance }
        val out = persons.filter { it.distanceTo(core) > maxDistance }
        ret.add(cluster)
        generateClusters(out, ret)
    }

}