package com.mohith.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.panel
import com.mohith.model.DeviceModel
import java.awt.Dimension
import javax.swing.*

class DeviceDialog(project: Project, private val deviceList: MutableList<DeviceModel>, private val dialogActionListener: DialogActionListener? = null) : DialogWrapper(project) {
    init {
        init()
        project.createNotification("ToolDroid", "Total Devices Connceted ${deviceList.size}")
    }

    override fun createCenterPanel(): JComponent? = panel {
        deviceList.forEach { device ->
            row {
                checkBox(device.getName()).component.apply {
                    name = device.getName()
                    isSelected = device.selected
                    addActionListener {
                        device.selected = isSelected
                    }
                }
            }
        }

    }.apply {
        minimumSize = Dimension(500, 350)
        preferredSize = Dimension(500, 350)
    }

    override fun doOKAction() {
        super.doOKAction()
        dialogActionListener?.onOk(deviceList)
    }

    override fun doCancelAction() {
        super.doCancelAction()
        dialogActionListener?.onCancel()
    }
}