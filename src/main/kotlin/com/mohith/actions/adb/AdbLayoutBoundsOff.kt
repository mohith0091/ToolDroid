package com.mohith.actions.adb

import com.android.ddmlib.NullOutputReceiver
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.mohith.model.DeviceModel
import com.mohith.utils.*

class AdbLayoutBoundsOff : AnAction(), DialogActionListener {
    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let { project ->
            project.adb {
                singleDevice {
                    onOk(it)
                }
                multiDevice {
                    val dialog = DeviceDialog(project, it, this@AdbLayoutBoundsOff)
                    dialog.show()
                }
            }


        }
    }


    override fun onOk(deviceList: MutableList<DeviceModel>) {
        deviceList.forEach {
            if (it.selected) {
                Thread {
                    it.device?.setLayoutBound(false)
                }.start()
            }
        }
    }

    override fun onCancel() {
    }
}