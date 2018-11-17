package com.example.demo.view.common

import javafx.beans.property.Property

interface IEditableField<P : Property<Number>> :Field{
    val editableField: P
}