package com.example.demo.view

import com.example.demo.view.common.IChartPoint
import javafx.collections.ObservableList
import javafx.scene.chart.XYChart
import tornadofx.*

class UIData(val first: UISnapshot) {

    val snapshots = listOf(first).toMutableList().observable()

    val charts: HashMap<String, ObservableList<XYChart.Data<Number, Number>>>

    val chartsNames: List<String>

    init {
        chartsNames = first.params.list
                .filter { it is IChartPoint }
                .map { it.name }

        charts = HashMap<String, ObservableList<XYChart.Data<Number, Number>>>().apply {
            chartsNames.forEach {
                put(it, emptyList<XYChart.Data<Number, Number>>().toMutableList().observable())
            }
        }

    }

    fun refreshChart() {
        charts.forEach { t, u ->
            u.clear()
            val add = snapshots.flatMap {
                it.getChartPointsByName(t)
            }
            u.addAll(add)
        }
    }

}