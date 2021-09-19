package com.github.salkaevruslan.pycharmplugintask.inspections

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.LocalInspectionToolSession
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.util.containers.stream
import com.jetbrains.python.psi.PyClass
import com.jetbrains.python.psi.PyFunction
import javax.swing.JLabel


class PsiInspection : LocalInspectionTool() {
    private var counter = 0

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PsiElementVisitor() {
            override fun visitElement(element: PsiElement) {
                if (element is PyFunction) {
                    val functionClass = element.containingClass
                    val functionName = element.name
                    if (checkClass(functionClass) && checkFunctionName(functionName) && element.identifyingElement != null) {
                        holder.registerProblem(
                            element.identifyingElement!!,
                            "Test names are not allowed to contain 'c'",
                            ProblemHighlightType.WARNING
                        )
                        counter++
                    }
                }
            }

            private fun checkFunctionName(name: String?): Boolean {
                return name != null && (name.contains('c') || name.contains("C")) && name.startsWith("test")
            }

            private fun checkClass(functionClass : PyClass?): Boolean {
                if (functionClass == null) return false
                return functionClass.superClassExpressions.stream().filter { it.text.contains("unittest.") }.count() != 0.toLong()
            }
        }
    }


    override fun inspectionStarted(session: LocalInspectionToolSession, isOnTheFly: Boolean) {
        counter = 0
    }

    override fun inspectionFinished(session: LocalInspectionToolSession, problemsHolder: ProblemsHolder) {
        val t = ToolWindowManager.getInstance(problemsHolder.project).getToolWindow("Custom inspection info")
        val x = t?.contentManager?.contents?.find { it.displayName == "Info Window" } ?: return
        (x.component as JLabel).text = counter.toString()
    }

    override fun isEnabledByDefault(): Boolean {
        return true
    }

    override fun isSuppressedFor(element: PsiElement): Boolean {
        return false
    }
}