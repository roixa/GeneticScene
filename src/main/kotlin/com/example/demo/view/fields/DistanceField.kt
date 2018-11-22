package com.example.demo.view.fields

import com.example.demo.controller.Strings
import com.example.demo.view.common.EditableDoubleField
import com.example.demo.view.common.IEditableField
import com.example.demo.view.common.UIParam
import javafx.beans.property.SimpleDoubleProperty
import tornadofx.*

class DistanceField  (pos: Int, value: Double)
    : UIParam(pos, value, Strings.titleDistance)
//        , IChartPoint by ChartPoint(pos, value)
        , IEditableField<SimpleDoubleProperty> by EditableDoubleField(Strings.titleDistance, value) {
    override val value: Number by editableField
}
