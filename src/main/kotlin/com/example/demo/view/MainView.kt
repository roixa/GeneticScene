package com.example.demo.view

import com.example.demo.app.Styles
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("Hello TornadoFX semen") {

    var firstNameField:  TextField by singleAssign()
    var lastNameField: TextField by singleAssign()

    override val root = vbox {
        hbox {
            label("First Name")
            firstNameField = textfield()
            tooltip("Writes input to the database")

        }
        hbox {
            label("Last Name")
            lastNameField = textfield()
        }
        button("LOGIN") {
            useMaxWidth = true
            action {
                println("Logging in as ${firstNameField.text} ${lastNameField.text}")
            }
        }
        linechart("Unit Sales Q2 2016", CategoryAxis(), NumberAxis()) {
            series("Product X") {
                data("MAR", 10245)
                data("APR", 23963)
                data("MAY", 15038)
            }
            series("Product Y") {
                data("MAR", 28443)
                data("APR", 22845)
                data("MAY", 19045)
            }
        }

    }
}