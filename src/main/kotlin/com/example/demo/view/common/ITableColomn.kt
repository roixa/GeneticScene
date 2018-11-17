package com.example.demo.view.common

import javafx.beans.property.Property

interface ITableColomn<P : Property<Number>> {
    fun getTableField(): P
}