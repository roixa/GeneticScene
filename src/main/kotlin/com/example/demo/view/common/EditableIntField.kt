package com.example.demo.view.common

import javafx.beans.property.SimpleIntegerProperty


class EditableIntField(name: String, vv: Int) : IEditableField<SimpleIntegerProperty> {
    override val editableField: SimpleIntegerProperty = SimpleIntegerProperty(this, name, vv)

    override val value: Number = vv

}