package com.example.demo.view.common

import javafx.scene.chart.XYChart

class ChartPoint(val x: Number, v: Number) : IChartPoint {

    override fun getChartProperty(value: Int): XYChart.Data<Number, Number> {
        print("getChartProperty value "+value)
        return XYChart.Data(x, value)

    }

}