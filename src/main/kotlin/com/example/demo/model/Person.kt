package com.example.demo.model

open class Person(val genDimension: Int, val genNumber: Int) : Randomly(genDimension) {


    companion object {

        fun generateRandomGender(genDimension: Int, genNumber: Int): Person = if (isTrueRandomly()) {
            Male(genDimension, genNumber)
        } else {
            Female(genDimension, genNumber)
        }


        fun generateNew(genDimension: Int, genNumber: Int): Person {
            return generateRandomGender(genDimension, genNumber).apply {
                generateGenes()
            }
        }

        fun fromParents(one: Person, two: Person): Person {
            return generateRandomGender(one.genDimension, one.genNumber).apply {
                combineParentGenes(one, two)
            }
        }
    }

    val genes = IntArray(genNumber)

    var age = 0

    private fun generateGenes() {
        for (i in 0..genNumber) {
            genes[i] = getNextValue()
        }
    }

    private fun combineParentGenes(one: Person, two: Person) {
        one.genes.zip(two.genes).forEachIndexed { index, pair ->
            genes[index] = combineGenesPair(pair.first, pair.second)
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