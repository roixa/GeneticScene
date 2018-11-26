package com.example.demo.model

import java.util.*

class Female(genDimension: Int, genNumber: Int, rand: Random) : Person(genDimension, genNumber, rand) {

    fun sex(person: Person, mutationChancePercent: Int): Person {
        val new = if (isTrueRandomly()) {
            Male(genDimension, genNumber, rand)
        } else {
            Female(genDimension, genNumber, rand)
        }
        combineParentGenes(new, person, mutationChancePercent)
        return new
    }

    private fun combineParentGenes(new: Person, parent: Person, mutationChancePercent: Int) {
        genes.zip(parent.genes).forEachIndexed { index, pair ->
            new.genes[index] = combineGenesPair(pair.first, pair.second, mutationChancePercent)
        }
    }

    private fun combineGenesPair(one: Int, two: Int, mutationChancePercent: Int): Int {
        val isOneInPriority = isTrueRandomly()

        return if (isTrueRandomly(mutationChancePercent)) {
            getNextRandomValue()
        } else {
            if (isOneInPriority) {
                one
            } else {
                two
            }
        }


    }


}
