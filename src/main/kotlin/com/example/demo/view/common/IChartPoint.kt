package com.example.demo.view.common

import javafx.scene.chart.XYChart

interface IChartPoint  {
    fun getChartProperty(): XYChart.Data<Number, Number>
}