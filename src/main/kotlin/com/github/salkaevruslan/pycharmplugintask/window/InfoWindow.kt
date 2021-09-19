package com.github.salkaevruslan.pycharmplugintask.window

import javax.swing.JLabel


class InfoWindow {
    private val myLabel = JLabel("")

    fun getContent(): JLabel {
        return myLabel
    }
}