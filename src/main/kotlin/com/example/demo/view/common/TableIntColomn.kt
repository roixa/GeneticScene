package com.example.demo.view.common

import javafx.beans.property.SimpleIntegerProperty

class TableIntColomn(val name: String, val value: Int) : ITableColomn<SimpleIntegerProperty> {
    override fun getTableField(): SimpleIntegerProperty {
        return SimpleIntegerProperty(this, name, value)
    }
}