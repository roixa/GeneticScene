package com.example.demo.view.common

import com.example.demo.model.Scene
import com.example.demo.view.fields.DimensionField
import com.example.demo.view.fields.NumberField
import com.example.demo.view.fields.PopulationField

class UISnapshot(scene: Scene, position: Int) {

    val params: List<UIParam>

    init {
        params = listOf(
                NumberField(position, scene.params.genNumber),
                DimensionField(position, scene.params.genDimension),
                PopulationField(position, scene.params.population)
        )
    }

    fun getTableColomnsByName(name: String) = params
            .filter { it.name == name }
            .filter { it is ITableColomn<*> }
            .map { it as ITableColomn<*> }
            .map { it.getTableField() }

    fun getChartPointsByName(name: String) = params
            .filter { it.name == name }
            .filter { it is IChartPoint }
            .map { it as IChartPoint }
            .map { it.getChartProperty() }

    fun getEditorFieldsByName(name: String) = params
            .filter { it.name == name }
            .filter { it is IEditableField<*> }
            .map { it as IEditableField<*> }
            .map { it.getEditableField() }


}