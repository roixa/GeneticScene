package com.example.demo.view.fields

import com.example.demo.controller.Strings
import com.example.demo.view.common.EditableIntField
import com.example.demo.view.common.IEditableField
import com.example.demo.view.common.UIParam
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class MaxAgeField (pos: Int, value: Int)
    : UIParam(pos, value, Strings.titleMaxAge)
//        , IChartPoint by ChartPoint(pos, value)
        , IEditableField<SimpleIntegerProperty> by EditableIntField(Strings.titleMaxAge, value) {
    override val value: Number by editableField
}
