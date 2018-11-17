package com.example.demo.view.common

import javafx.beans.property.SimpleDoubleProperty

class EditableDoubleField(val name: String, val v: Double) : IEditableField<SimpleDoubleProperty> {

    override val editableField: SimpleDoubleProperty = SimpleDoubleProperty(this, name, v)

    override val value: Number = v

}