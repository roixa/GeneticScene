package com.example.demo.view

import com.example.demo.controller.MainController
import com.example.demo.controller.Strings
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import javafx.util.converter.NumberStringConverter
import tornadofx.*

class MainView : View("Hello TornadoFX semen") {

    val controller: MainController by inject()

    override val root = BorderPane()

    val dimensionProperty = SimpleIntegerProperty(this, "Dimension", 10)
    val numberProperty = SimpleIntegerProperty(this, "Number", 100)
    val populationProperty = SimpleIntegerProperty(this, "Population", 1000)
    val relativeDistanceProperty = SimpleDoubleProperty(this, "RelativeDistance", 0.1)
    val newEffectivelyChangesPercentProperty = SimpleIntegerProperty(this, "NewEffectivelyChangesPercent", 0)


    var scenesTable: TableView<UIScene> by singleAssign()

    val scenes = emptyList<UIScene>().toMutableList().observable()

    val chartPoints = scenes.mapIndexed { index, uiScene ->
        XYChart.Data<Number, Number>(index, uiScene.genDimension)
    }.observable()

    var prevSelection: UIScene? = null

    lateinit var tableView: TableView<UIScene>
    lateinit var chartView: LineChart<*, *>

    init {
        controller.startNewNistory(scenes)

        with(root) {
            left {
                tableView = tableview(scenes) {
                    scenesTable = this
                    column(Strings.titleDimension, UIScene::genDimensionProperty)
                    column(Strings.titleNumber, UIScene::genNumberProperty)

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
                        field(Strings.titleDimension) {
                            textfield(dimensionProperty, NumberStringConverter())
                        }
                        field(Strings.titleNumber) {
                            textfield(numberProperty, NumberStringConverter())
                        }
                        button("Step").action {
                            save()
                        }
                    }
                }
            }

            right {
                chartView = linechart("example chart", NumberAxis(), NumberAxis()) {
                    series("dimensions", chartPoints)
                }
            }
        }
    }

    private fun editScene(scene: UIScene?) {
        if (scene != null) {
            prevSelection?.apply {
                dimensionProperty.unbindBidirectional(genDimensionProperty)
                numberProperty.unbindBidirectional(genNumberProperty)
            }
            dimensionProperty.bindBidirectional(scene.genDimensionProperty)
            numberProperty.bindBidirectional(scene.genNumberProperty)
            prevSelection = scene
        }
    }

    private fun save() {
        tableView.requestResize()
        val list = scenes.mapIndexed { index, uiScene ->
            XYChart.Data<Number, Number>(index, uiScene.genDimension)
        }.observable()

        chartPoints.clear()
        chartPoints.addAll(list)

        controller.step(scenes)
        val person = scenesTable.selectedItem
        println("Saving ${person?.genDimension} / ${person?.genNumber}")
    }

}