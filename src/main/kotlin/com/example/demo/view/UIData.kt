package com.example.demo.view

import com.example.demo.view.common.IChartPoint
import javafx.application.Platform
import javafx.collections.ObservableList
import javafx.scene.chart.XYChart
import tornadofx.*

class UIData(val first: UISnapshot) {

    val snapshots = listOf(first).toMutableList().observable()


    val chartsNames: List<String> = first.params.paramsList
            .filter { it is IChartPoint }
            .map { it.name }

    val charts: HashMap<String, ObservableList<XYChart.Data<Number, Number>>>

    val scatterChartPoints = emptyList<XYChart.Data<Number, Number>>().toMutableList().observable()

    val barChartPoints = emptyList<XYChart.Data<String, Number>>().toMutableList().observable()

    init {

        charts = HashMap<String, ObservableList<XYChart.Data<Number, Number>>>().apply {
            chartsNames.forEach {
                put(it, emptyList<XYChart.Data<Number, Number>>().toMutableList().observable())
            }
        }

    }


    fun refreshChart(snapshot: UISnapshot) {
        Platform.runLater {
            scatterChartPoints.clear()
            scatterChartPoints.addAll(snapshot.params.geneticPoints.map { it.getChartProperty() })

            barChartPoints.clear()
            barChartPoints.addAll(snapshot.params.clusters.map {
                XYChart.Data<String, Number>(it.first, it.second)
            })

            charts.forEach { t, u ->
                u.clear()
                val add = snapshots.flatMap {
                    it.getChartPointsByName(t)
                }
                u.addAll(add)
            }

        }

    }


}