package com.example.demo.model

import java.util.*

class Female(genDimension: Int, genNumber: Int, rand: Random) : Person(genDimension, genNumber, rand) {

    fun sex(person: Person): Person {
        val new = if (isTrueRandomly()) {
            Male(genDimension, genNumber, rand)
        } else {
            Female(genDimension, genNumber, rand)
        }
        combineParentGenes(new, person)
        return new
    }

    private fun combineParentGenes(new: Person, parent: Person) {
        genes.zip(parent.genes).forEachIndexed { index, pair ->
            new.genes[index] = combineGenesPair(pair.first, pair.second)
        }
    }

    private fun combineGenesPair(one: Int, two: Int): Int {
        val isOneInPriority = isTrueRandomly()
        val oneIsDominant = one > 0
        val twoIsDominant = two > 0
        return if (oneIsDominant) {
            if (twoIsDominant) {
                if (isOneInPriority) {
                    one
                } else {
                    two
                }
            } else {
                one
            }
        } else {
            if (twoIsDominant) {
                two
            } else {
                if (isOneInPriority) {
                    one
                } else {
                    two
                }
            }
        }

    }
}