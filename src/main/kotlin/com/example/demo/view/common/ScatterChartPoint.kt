package com.example.demo.view.common

import javafx.scene.chart.XYChart

class ScatterChartPoint(val x: Number, val y: Number) : IScatterChartPoint {

    override fun getChartProperty(): XYChart.Data<Number, Number> {
        return XYChart.Data(x, y)
    }

}