package com.example.demo.view.fields

import com.example.demo.controller.Strings
import com.example.demo.view.common.EditableIntField
import com.example.demo.view.common.IEditableField
import com.example.demo.view.common.UIParam
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

class AttributesField(pos: Int, value: Int)
    : UIParam(pos, value, Strings.titleAttributes)
//        , IChartPoint by ChartPoint(pos, value)
        , IEditableField<SimpleIntegerProperty> by EditableIntField(Strings.titleAttributes, value) {
    override val value: Number by editableField
}
