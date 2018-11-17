package com.example.demo.view.fields

import com.example.demo.controller.Strings
import com.example.demo.view.common.*
import javafx.beans.property.SimpleIntegerProperty

class DimensionField(pos: Int, value: Int)
    : UIParam(pos, value, Strings.titleDimension)
        , IChartPoint by ChartPoint(pos, value)
        , ITableColomn<SimpleIntegerProperty> by TableIntColomn(Strings.titleDimension, value)
        , IEditableField<SimpleIntegerProperty> by EditableIntField(Strings.titleDimension, value)
