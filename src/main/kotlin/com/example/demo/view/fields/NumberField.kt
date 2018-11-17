package com.example.demo.view.fields

import com.example.demo.controller.Strings
import com.example.demo.view.common.*
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class NumberField(val pos: Int, value: Int)
    : UIParam(pos, value, Strings.titleNumber)
        , IChartPoint by ChartPoint(pos, value)
        , IEditableField<SimpleIntegerProperty> by EditableIntField(Strings.titleNumber, value) {
    override val value: Number by editableField
}

