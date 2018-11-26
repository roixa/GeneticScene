package com.example.demo.view.fields

import com.example.demo.controller.Strings
import com.example.demo.view.common.*
import javafx.beans.property.SimpleDoubleProperty
import tornadofx.*

class MaxRelativeDistanceField (pos: Int, value: Double)
    : UIParam(pos, value, Strings.titleMaxRelativeDistance)
        , IChartPoint by ChartPoint(pos, value)
        , IEditableField<SimpleDoubleProperty> by EditableDoubleField(Strings.titleMaxRelativeDistance, value) {
    override val value: Number by editableField
}
