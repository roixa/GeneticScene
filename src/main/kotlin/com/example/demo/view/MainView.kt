package com.example.demo.view

import com.example.demo.controller.MainController
import com.example.demo.controller.Strings
import com.example.demo.view.common.IEditableField
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import javafx.util.converter.NumberStringConverter
import tornadofx.*

class MainView : View("Roix genetic algorithm") {

    val controller: MainController by inject()

    override val root = BorderPane()

    lateinit var tableView: TableView<UISnapshot>

    init {
        controller.startNewNistory()
        with(root) {
            left {
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
            center {
                tableView = tableview(controller.data.snapshots) {
                    column(Strings.titleEffectivity, UISnapshot::effecrivityColomn)
                    column(Strings.titleDistance, UISnapshot::distanceColomn)
                    column(Strings.titlePopulation, UISnapshot::populationColomn)
                    column(Strings.titleMaxRelativeDistance, UISnapshot::relativeDistanceColomn)
                    column(Strings.titleNewEffectivelyChangesPercent, UISnapshot::newEffictivityChangesPersentColomnm)

                    column(Strings.titleMaxAge, UISnapshot::maxAgeColomn)
                    column(Strings.titleMaxReproductiveAge, UISnapshot::maxReproductiveAgeColomn)

                    column(Strings.titleNumber, UISnapshot::numberColomn)
                    column(Strings.titleDimension, UISnapshot::dimensionColomn)
                    column(Strings.titleAttributes, UISnapshot::attributesColomn)

                    columnResizePolicy = SmartResize.POLICY
                    selectionModel.selectedItemProperty().onChange {
                        editScene(it)
                    }

                }

            }
            right {
                vbox {
                    scatterchart(Strings.titlePopulation, NumberAxis(), NumberAxis()) {
                        series(Strings.titlePopulation, controller.data.scatterChartPoints)
                    }
                    barchart(Strings.titleClusters, CategoryAxis(), NumberAxis()) {
                        series(Strings.titlePopulation, controller.data.barChartPoints)
                    }
                }

            }
            bottom {
                hbox {
                    controller.data.chartsNames.forEach {
                        linechart(it, NumberAxis(), NumberAxis()) {
                            series(it, controller.data.charts[it])
                        }
                    }
                }


            }
        }
    }

    private fun editScene(scene: UISnapshot?) {
        if (scene != null) {
            controller.data.refreshChart(scene)
        }
    }

    private fun save() {
        print("Saving ${controller.data.first.params.toParams()}")
        controller.step()
        tableView.requestResize()
    }

}