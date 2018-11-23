package com.example.demo.view

import com.example.demo.controller.MainController
import com.example.demo.controller.Strings
import com.example.demo.view.common.IEditableField
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import javafx.util.converter.NumberStringConverter
import tornadofx.*

class MainView : View("Roix genetic algorithm") {

    val controller: MainController by inject()

    override val root = BorderPane()


//    var prevSelection: UISnapshot? = null

    lateinit var tableView: TableView<UISnapshot>
    lateinit var chartView: LineChart<*, *>

    init {
        controller.startNewNistory()
        with(root) {
            left {

                tableView = tableview(controller.data.snapshots) {
                    column(Strings.titleNumber, UISnapshot::numberColomn)
                    column(Strings.titleDimension, UISnapshot::dimensionColomn)
                    column(Strings.titleAttributes, UISnapshot::attributesColomn)
                    column(Strings.titlePopulation, UISnapshot::populationColomn)
                    column(Strings.titleEffectivity, UISnapshot::effecrivityColomn)
                    column(Strings.titleDistance, UISnapshot::distanceColomn)

                    selectionModel.selectedItemProperty().onChange {
                        //                        editScene(it)
//                        prevSelection = it
                    }
                    columnResizePolicy = SmartResize.POLICY
                }
            }
            center {
                form {
                    fieldset("Edit scene") {
                        controller.data.first.getEditableParams()
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
                chartView = linechart("Values", NumberAxis(), NumberAxis()) {
                    controller.data.chartsNames
                            .forEach {
                                series(it, controller.data.charts[it])
                            }
                }
            }
        }
    }

//    private fun editScene(scene: UISnapshot?) {
//        if (scene != null) {
//            prevSelection?.apply {
//                this.getEditableParams().zip(controller.data.first.getEditableParams()).forEach {
//                    val from = it.second as IEditableField<*>
//                    val to = it.first as IEditableField<*>
//                    from.editableField.unbindBidirectional(to.editableField)
//                }
//            }
//            scene.getEditableParams().zip(controller.data.first.getEditableParams()).forEach {
//                val from = it.second as IEditableField<*>
//                val to = it.first as IEditableField<*>
//                from.editableField.bindBidirectional(to.editableField)
//            }
//            prevSelection = scene
//        }
//    }

    private fun save() {
        print("Saving ${controller.data.first.params.toParams()}")
        controller.step()
        tableView.requestResize()
    }

}