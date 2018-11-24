package com.example.demo.view.common

import javafx.scene.chart.XYChart

interface IScatterChartPoint {
    fun getChartProperty(): XYChart.Data<Number, Number>

}