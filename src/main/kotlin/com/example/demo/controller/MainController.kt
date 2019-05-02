package com.example.demo.controller

import com.example.demo.model.Params
import com.example.demo.model.Scene
import com.example.demo.model.TestAlg
import com.example.demo.view.UIData
import com.example.demo.view.UISnapshot
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import tornadofx.*

class MainController : Controller() {

    val scenes = emptyList<Scene>().toMutableList()

    val data = UIData(UISnapshot(Scene(null, Params.getStartedData()), 0))

    fun startNewNistory() {
        testAlgrs()
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
            try {
                val size = data.snapshots.size
                data.snapshots.add(UISnapshot(scenes.last(), size - 1))
                data.refreshChart(data.snapshots.last())
            } catch (e: Exception) {

            }
        }


    }

    private suspend fun createNewScene(lastScene: Scene? = null): Scene {
        return withContext(IO) { Scene(lastScene, data.first.params.toParams()) }
    }

    fun testAlgrs() {
        val result1 = TestAlg.check("{()}[]")
        val result2 = TestAlg.check("{[}]")
        val x = 1

    }

}