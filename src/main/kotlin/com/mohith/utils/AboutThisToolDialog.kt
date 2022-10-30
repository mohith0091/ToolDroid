package com.mohith.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.panel
import com.mohith.model.DeviceModel
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JTextPane

class AboutThisToolDialog(project: Project) : DialogWrapper(project) {
    init {
        init()
    }

    override fun createCenterPanel(): JComponent? = panel {
        row {
            text("About This Tool").bold()
        }
        row {
            text("This tool is developed by")
            text("Mohith").bold()
        }
        row {
            text("Note : ").bold()
            text("To work with this tool you will need ADB (Android Debug Bridge) and Scrcpy which is available in Github.")
        }
        row {
            text("Features").bold()
        }
        row {
            text("* Shows all connected devices")
        }
        row {
            text("* Screen mirroring - requires SCRCPY")
        }
        row {
            text("* Rebooting the device")
        }
        row {
            text("* Rebooting the device into Recovery")
        }
        row {
            text("* Rebooting the device into Fastboot or Bootloader")
        }
        row {
            text("* Turing OFF/ON the Layout Bound")
        }
        row {
            browserLink("Progect Link","https://github.com/mohith0091/ToolDroid")
        }
        row {
            browserLink("Scrcpy Link","https://github.com/Genymobile/scrcpy")
        }


    }.apply {
        minimumSize = Dimension(500, 350)
        preferredSize = Dimension(500, 350)
    }

}