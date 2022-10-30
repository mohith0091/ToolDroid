package com.mohith.actions.scrcpy

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.util.ExecUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.mohith.model.DeviceModel
import com.mohith.utils.*


class StartScrcpyAction : AnAction(), DialogActionListener {
    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let { project ->
            project.adb {
                singleDevice {
                    onOk(it)
                }
                multiDevice {
                    val dialog = DeviceDialog(project, it, this@StartScrcpyAction)
                    dialog.show()
                }
            }
        }
    }

    override fun onOk(deviceList: MutableList<DeviceModel>) {
        deviceList.forEach {
            if (it.selected) {
                Thread {
                    it.startScrcpy()
                }.start()
            }
        }
    }

    override fun onCancel() {
    }
}