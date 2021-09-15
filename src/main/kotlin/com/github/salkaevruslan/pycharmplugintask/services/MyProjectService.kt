package com.github.salkaevruslan.pycharmplugintask.services

import com.intellij.openapi.project.Project
import com.github.salkaevruslan.pycharmplugintask.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
