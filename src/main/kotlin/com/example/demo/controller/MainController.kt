package com.example.demo.controller

import com.example.demo.model.Scene
import com.example.demo.view.UIData
import com.example.demo.view.common.UISnapshot
import tornadofx.*

class MainController : Controller() {

    val scenes = emptyList<Scene>().toMutableList()

    fun startNewNistory(data: UIData) {
        val add = createNewScene(scenes.lastOrNull())
        scenes.apply {
            clear()
            add(add)
        }
        val size = data.snapshots.size
        data.snapshots.apply {
            clear()
            add(UISnapshot(add, size))
        }
    }

    fun step(data: UIData) {
        val add = createNewScene(scenes.lastOrNull())
        scenes.add(add)
        val size = data.snapshots.size
        data.snapshots.add(UISnapshot(add, size - 1))
    }

    private fun createNewScene(lastScene: Scene? = null): Scene {
        return Scene(lastScene)
    }

}