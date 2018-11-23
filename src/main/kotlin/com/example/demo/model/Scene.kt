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
    val maxReproductiveAge: Int = params.maxAge
    val maxSexes: Int = params.maxSexes
    val relativeDistance: Double = params.relativeDistance
    val newEffectivityChangesPersent: Int = params.newEffectivelyChangesPercent
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

    private fun initGeneratedParams() {

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


    private fun isNeedToRegenerate() = genDimension != oldScene?.genDimension && genDimension != oldScene?.genNumber

}