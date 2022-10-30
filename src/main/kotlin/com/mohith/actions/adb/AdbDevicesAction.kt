package com.mohith.actions.adb

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.mohith.model.DeviceModel
import com.mohith.utils.*

class AdbDevicesAction : AnAction() ,DialogActionListener{
    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let { project ->
            project.adb {
                val dialog = DeviceDialog(project, devices.map { it.getModel() } as MutableList<DeviceModel>)
                dialog.show()
            }
        }
    }

    override fun onOk(deviceList: MutableList<DeviceModel>) {

    }


    override fun onCancel() {
    }
}

