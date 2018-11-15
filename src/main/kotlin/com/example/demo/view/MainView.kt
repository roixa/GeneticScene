package com.example.demo.view

import com.example.demo.controller.MainController
import com.example.demo.controller.Strings
import com.example.demo.controller.UIScene
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
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

    val persons = listOf(UIScene(), UIScene()).observable()

    var prevSelection: UIScene? = null

    init {
        controller.startNewNistory()
        with(root) {
            center {
                tableview(persons) {
                    scenesTable = this
                    column(Strings.titleDimension, UIScene::genDimensionProperty)
                    column(Strings.titleNumber, UIScene::genNumberProperty)

                    selectionModel.selectedItemProperty().onChange {
                        editScene(it)
                        prevSelection = it
                    }
                }
            }

            right {
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
        persons.add(UIScene())
        controller.step()
        val person = scenesTable.selectedItem
        println("Saving ${person?.genDimension} / ${person?.genNumber}")
    }

}