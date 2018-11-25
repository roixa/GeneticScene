package com.example.demo.view.common

import javafx.scene.chart.XYChart

class ChartPoint(val x: Number,val v: Number) : IChartPoint {

    override fun getChartProperty(): XYChart.Data<Number, Number> {
        print("getChartProperty value "+v)
        return XYChart.Data(x, v)

    }

}