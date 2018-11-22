package com.example.demo.controller

import com.example.demo.model.Scene
import com.example.demo.view.UIData
import com.example.demo.view.common.UISnapshot
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import tornadofx.*

class MainController : Controller() {

    val scenes = emptyList<Scene>().toMutableList()

    val data = UIData(UISnapshot(Scene(), 0))

    fun startNewNistory() {
        runBlocking {
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
    }

    fun step() {

        GlobalScope.launch {
            val add = createNewScene(scenes.lastOrNull())
            scenes.add(add)
        }.invokeOnCompletion {
            val size = data.snapshots.size
            data.snapshots.add(UISnapshot(scenes.last(), size - 1))
            data.refreshChart()
        }


    }

    private suspend fun createNewScene(lastScene: Scene? = null): Scene {
        return withContext(IO) { Scene(lastScene) }
    }


}