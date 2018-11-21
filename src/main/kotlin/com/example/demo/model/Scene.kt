package com.example.demo.model

class Scene(val oldScene: Scene? = null
            , genDimension: Int = 10
            , genNumber: Int = 100
            , attributesNumber: Int = 10
            , var population: Int = 300
            , val maxAge: Int = 10
            , val maxSexes: Int = 3
            , val relativeDistance: Double = 0.1
            , newEffectivityChangesPersent: Int = 0) : Randomly(genDimension) {

    val params = Params(genDimension, genNumber, attributesNumber, population, relativeDistance, newEffectivityChangesPersent)

    val conditions: Array<IntArray>

    val effectivity: IntArray

    val persons: MutableList<Person>

    val personsEffectivity: Int

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
                    Male(genDimension, genNumber)
                } else {
                    Female(genDimension, genNumber)
                }
            }
        } else {
            conditions = oldScene.conditions
            effectivity = oldScene.effectivity
            persons = oldScene.persons
            bornNewPopulation()
            destroyOldPopulation()
            population = persons.size
        }
        personsEffectivity = persons.filter { it is Male }
                .map { it as Male }
                .sumBy { it.getPersonalEffectivity(conditions, effectivity) }
    }

    private fun bornNewPopulation() {
        val maxAllowedDistance = calculatesAbsoluteMaxDistance()

        val males = persons.filter { it is Male }
                .map { it as Male }
                .sortedBy { it.getPersonalEffectivity(conditions, effectivity) }

        persons.filter { it is Female }
                .forEach {
                    it as Female
                    interactPair(males, it, maxAllowedDistance)
                }
    }

    private fun destroyOldPopulation() {
        persons.removeAll {
            it.age > maxAge
        }
        persons.forEach {
            it.age++
        }
    }

    private fun interactPair(males: List<Male>, female: Female, maxAllowedDistance: Double) {
        val choosen = males
                .filter { it.distanceTo(female) < maxAllowedDistance }
                .firstOrNull { it.sexes < maxSexes }
        if (choosen != null) {
            persons.add(female.sex(choosen))
        }

        choosen?.sexes?.plus(1)
    }

    private fun calculatesAbsoluteMaxDistance(): Double {
        var max = 0
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