package com.github.salkaevruslan.pycharmplugintask.window

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory




class InfoWindowFactory: ToolWindowFactory {
    override fun createToolWindowContent(p0: Project, p1: ToolWindow) {
        val myToolWindow = InfoWindow()
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content: Content = contentFactory.createContent(myToolWindow.getContent(), "Info Window", false)
        p1.contentManager.addContent(content)
    }
}

