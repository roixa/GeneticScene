package com.example.demo.model

import com.example.demo.controller.Logger
import java.util.*

class Scene(val oldScene: Scene? = null
            , genDimension: Int = 10
            , genNumber: Int = 10
            , attributesNumber: Int = 10
            , var population: Int = 10
            , val maxAge: Int = 2
            , val maxSexes: Int = 3
            , val relativeDistance: Double = 1.0
            , newEffectivityChangesPersent: Int = 0) : Randomly(genDimension, Random(System.currentTimeMillis())) {

    val params = Params(genDimension, genNumber, attributesNumber, population, relativeDistance, newEffectivityChangesPersent)

    val conditions: Array<IntArray>

    val effectivity: IntArray

    val persons: MutableList<Person>

    val personsEffectivity: Int

    val maxDistance: Double

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
            maxDistance = calculatesAbsoluteMaxDistance()

            Logger.logInitalData(conditions, effectivity)
        } else {
            conditions = oldScene.conditions
            effectivity = oldScene.effectivity
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
            maxDistance = calculatesAbsoluteMaxDistance()
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

        persons.filter { it is Female }
                .forEach {
                    it as Female
                    interactPair(males, it, maxDistance)
                }
    }

    private fun destroyOldPopulation() {
        persons.removeIf {
            it.age > maxAge
        }
    }

    private fun interactPair(males: List<Male>, female: Female, maxAllowedDistance: Double) {
        val choosen = males
                .filter { it.distanceTo(female) < maxAllowedDistance }
                .firstOrNull { it.sexes < maxSexes }
        if (choosen != null) {
            persons.add(female.sex(choosen))
            val sexes = choosen.sexes
            choosen.sexes = sexes + 1

        }
    }

    private fun calculatesAbsoluteMaxDistance(): Double {
        var max = 0.0
        persons.forEach { first ->
            persons.forEach { second ->
                val d = first.distanceTo(second)
                if (d > max) {
                    max = d
                }
            }
        }
        return max.times(relativeDistance)
    }


    private fun isNeedToRegenerate() = params.isBaseParametersChanged(oldScene?.params)

}