package com.mohith.actions.adb

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.mohith.model.DeviceModel
import com.mohith.utils.*

class AdbFastBootMode:AnAction(), DialogActionListener {
    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let { project ->
            project.adb {
                singleDevice {
                    onOk(it)
                }
                multiDevice {
                    val dialog = DeviceDialog(project, it, this@AdbFastBootMode)
                    dialog.show()

                }
            }
        }
    }

    override fun onOk(deviceList: MutableList<DeviceModel>) {
        deviceList.forEach {
            if (it.selected) {
                Thread {
                    it.adbFastbootReboot()
                }.start()
            }
        }
    }

    override fun onCancel() {

    }
}