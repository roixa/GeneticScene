package com.example.demo.view.common

import javafx.scene.chart.XYChart

class ChartPoint(val x: Number, val y: Number) : IChartPoint {
    override fun getChartProperty(): XYChart.Data<Number, Number> {
        return XYChart.Data(x, y)
    }
}