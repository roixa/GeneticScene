package com.example.demo.view.fields

import com.example.demo.controller.Strings
import com.example.demo.view.common.IScatterChartPoint
import com.example.demo.view.common.ScatterChartPoint
import com.example.demo.view.common.UIParam

class GeneticPointField(x: Double, y: Double)
    : UIParam(x, y, Strings.titlePopulation)
        , IScatterChartPoint by ScatterChartPoint(x, y) {

}