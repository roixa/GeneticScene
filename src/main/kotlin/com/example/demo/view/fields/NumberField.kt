package com.example.demo.view.fields

import com.example.demo.controller.Strings
import com.example.demo.view.common.*
import javafx.beans.property.SimpleIntegerProperty

class NumberField(pos: Int, value: Int)
    : UIParam(pos, value, Strings.titleNumber)
        , IChartPoint by ChartPoint(pos, value)
        , ITableColomn<SimpleIntegerProperty> by TableIntColomn(Strings.titleNumber, value)
        , IEditableField<SimpleIntegerProperty> by EditableIntField(Strings.titleNumber, value)
