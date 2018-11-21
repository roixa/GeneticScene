package com.example.demo.view

import com.example.demo.controller.MainController
import com.example.demo.controller.Strings
import com.example.demo.model.Scene
import com.example.demo.view.common.IEditableField
import com.example.demo.view.common.UISnapshot
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import javafx.util.converter.NumberStringConverter
import tornadofx.*

class MainView : View("Hello TornadoFX semen") {

    val controller: MainController by inject()

    override val root = BorderPane()


    val mData = UIData(UISnapshot(Scene(), 0))

    var prevSelection: UISnapshot? = null

    lateinit var tableView: TableView<UISnapshot>
    lateinit var chartView: LineChart<*, *>

    init {
        controller.startNewNistory(mData)
        with(root) {
            left {

                tableView = tableview(mData.snapshots) {
                    column(Strings.titleNumber, UISnapshot::numberColomn)
                    column(Strings.titleDimension, UISnapshot::dimensionColomn)
                    column(Strings.titleAttributes, UISnapshot::attributesColomn)
                    column(Strings.titleEffectivity, UISnapshot::effecrivityColomn)


                    selectionModel.selectedItemProperty().onChange {
                        editScene(it)
                        prevSelection = it
                    }
                    columnResizePolicy = SmartResize.POLICY
                }
            }
            center {
                form {
                    fieldset("Edit scene") {
                        mData.first.getEditableParams()
                                .forEach {
                                    field(it.name) {
                                        val cast = it as IEditableField<*>
                                        textfield(cast.editableField, NumberStringConverter())
                                    }
                                }
                        button("Step").action {
                            save()
                        }
                    }
                }
            }
            right {
                chartView = linechart("example chart", NumberAxis(), NumberAxis()) {
                    mData.chartsNames
                            .forEach {
                                series(it, mData.charts[it])
                            }
                }
            }
        }
    }

    private fun editScene(scene: UISnapshot?) {
        if (scene != null) {
            prevSelection?.apply {
                this.getEditableParams().zip(mData.first.getEditableParams()).forEach {
                    val from = it.second as IEditableField<*>
                    val to = it.first as IEditableField<*>
                    from.editableField.unbindBidirectional(to.editableField)
                }
            }
            scene.getEditableParams().zip(mData.first.getEditableParams()).forEach {
                val from = it.second as IEditableField<*>
                val to = it.first as IEditableField<*>
                from.editableField.bindBidirectional(to.editableField)
            }
            prevSelection = scene
        }
    }

    private fun save() {

        controller.step(mData)
        tableView.requestResize()
        mData.refreshChart()
        val person = tableView.selectedItem
        println("Saving ${person?.params?.map { it.value }}")
    }

}