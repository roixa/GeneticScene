package com.example.demo.controller

import com.example.demo.model.Person

object Logger {

    val isEnabled = false

    fun logInitalData(conditions: Array<IntArray>, effectivity: IntArray) {
        if (isEnabled) {
            print("=================== initial data ================\n")
            print("=================== start ================\\n\"")
            print("=================== conditions ================\\n\"")
            print(conditions.map { it.toString() } + "\n")
            print("=================== effectivity ================\\n\"")
            print(effectivity.map { it.toString() } + "\n")
            print("=================== sizes ================\\n\"")

            print("conditions ${conditions.first().size} effectivity ${effectivity.size}" + "\n")
            print("=================== end ================\\n\"")
        }

    }

    fun logPopulation(persons: List<Person>) {
        if (isEnabled) {
            print("=================== persons ================\\n\"")
            print("persons size ${persons.size}" + "\n")
            print("=================== start ================\\n\"")
            print(persons.map { it.toString() } + "\n")
            print("=================== end ================\\n\"")

        }
    }


}