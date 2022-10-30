package com.mohith.actions.adb

import com.android.ddmlib.NullOutputReceiver
import com.android.tools.idea.wearpairing.executeShellCommand
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.mohith.model.DeviceModel
import com.mohith.utils.*
import org.jetbrains.kotlin.load.java.structure.impl.convert

class AdbLayoutBoundsON : AnAction(), DialogActionListener {
    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let { project ->
            project.adb {
                singleDevice {
                    onOk(it)
                }
                multiDevice {
                    val dialog = DeviceDialog(project, it, this@AdbLayoutBoundsON)
                    dialog.show()
                }
            }

        }
    }

    override fun onOk(deviceList: MutableList<DeviceModel>) {
        deviceList.forEach {
            if (it.selected) {
                Thread {
                    it.device?.setLayoutBound(true)
                }.start()
            }
        }
    }

    override fun onCancel() {
    }
}