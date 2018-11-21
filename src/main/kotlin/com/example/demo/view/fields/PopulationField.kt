package com.example.demo.view.fields

import com.example.demo.controller.Strings
import com.example.demo.view.common.EditableIntField
import com.example.demo.view.common.IEditableField
import com.example.demo.view.common.UIParam
import javafx.beans.property.SimpleIntegerProperty

class PopulationField (pos: Int, value: Int)
    : UIParam(pos, value, Strings.titlePopulation)
//        , IChartPoint by ChartPoint(pos, value)
        , IEditableField<SimpleIntegerProperty> by EditableIntField(Strings.titlePopulation, value)
