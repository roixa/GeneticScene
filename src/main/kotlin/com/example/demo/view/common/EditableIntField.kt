package com.example.demo.view.common

import javafx.beans.property.SimpleIntegerProperty

class EditableIntField(val name: String, val value: Int) : IEditableField<SimpleIntegerProperty> {
    override fun getEditableField(): SimpleIntegerProperty {
        return SimpleIntegerProperty(this, name, value)
    }
}