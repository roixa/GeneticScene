package com.example.demo.model

class Female(genDimension: Int, genNumber: Int) : Person(genDimension, genNumber) {

    fun sex(person: Person): Person {
        val new = if (isTrueRandomly()) {
            Male(genDimension, genNumber)
        } else {
            Female(genDimension, genNumber)
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