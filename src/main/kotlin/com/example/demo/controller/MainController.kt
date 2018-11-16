package com.example.demo.controller

import com.example.demo.model.Scene
import tornadofx.*

class MainController : Controller() {

    val scenes = emptyList<Scene>().toMutableList()

    fun startNewNistory(data: MutableList<UIScene>) {
        val add = createNewScene()
        scenes.apply {
            clear()
            add(add)
        }
        data.apply {
            clear()
            add(UIScene(add))
        }
    }

    fun step(data: MutableList<UIScene>) {
        val add = createNewScene()
        scenes.add(add)
        data.add(UIScene(add))
    }

    private fun createNewScene(lastScene: Scene? = null): Scene {
        return Scene(lastScene)
    }

}