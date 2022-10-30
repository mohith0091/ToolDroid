package com.mohith.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.mohith.utils.AboutThisToolDialog

class AboutThisTool:AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let {
            val dialog = AboutThisToolDialog(it)
            dialog.show()
        }
    }
}