package com.example.demo.view.common

import javafx.beans.property.SimpleDoubleProperty

class EditableDoubleField(val name: String, val value: Double) : IEditableField<SimpleDoubleProperty> {
    override fun getEditableField(): SimpleDoubleProperty {
        return SimpleDoubleProperty(this, name, value)
    }
}