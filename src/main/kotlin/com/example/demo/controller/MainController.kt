package com.example.demo.controller

import tornadofx.*
import java.util.*

class MainController : Controller() {

    val data = emptyList<UIScene>().toMutableList()

    fun startNewNistory() {
        data.clear()
        data.add(UIScene())
    }

    fun step() {
        data.add(UIScene())
    }


}