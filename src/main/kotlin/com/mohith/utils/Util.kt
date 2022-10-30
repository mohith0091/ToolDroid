package com.mohith.utils

import com.android.ddmlib.AndroidDebugBridge
import com.android.ddmlib.IDevice
import com.android.ddmlib.NullOutputReceiver
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.util.ExecUtil
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.mohith.model.DeviceModel
import org.jetbrains.android.sdk.AndroidSdkUtils


/**
For creating notifications
 */
fun Project.createNotification(title: String, message: String) {
    val noti = NotificationGroup(title, NotificationDisplayType.BALLOON, true)
    noti.createNotification(
        title,
        message,
        NotificationType.INFORMATION,
        null
    ).notify(this)
}

/**
For handling ADB
 */
fun Project.adb(block: AndroidDebugBridge.(AndroidDebugBridge) -> Unit): AndroidDebugBridge? {
    val adb = AndroidSdkUtils.getDebugBridge(this)
    adb?.block(adb)
    return adb
}

/**
Turn ON or OFF Layout Bounds
 */
fun IDevice.setLayoutBound(enable: Boolean) {
    this.executeShellCommand("setprop debug.layout $enable ; service call activity 1599295570", NullOutputReceiver())
}

/**
Converts IDevice model to DeviceModel
 */
fun IDevice.getModel(): DeviceModel {
    return DeviceModel(this.toString(), true, this)
}

/**
Extension function for ADB if only one device is connected
 */
fun AndroidDebugBridge.singleDevice(block: (MutableList<DeviceModel>) -> Unit) {
    devices.takeIf { devices.size == 1 }?.apply {
        block(devices.convert())
    }
}

/**
Extension function for ADB if Multi device is connected
 */
fun AndroidDebugBridge.multiDevice(block: (MutableList<DeviceModel>) -> Unit) {
    devices.takeIf { devices.size != 1 }?.apply {
        block(devices.convert())
    }
}

/**
SCRCPY start command
 */
fun DeviceModel.startScrcpy() {
    ExecUtil.execAndGetOutput(GeneralCommandLine("scrcpy", "-s", device.toString()))
}

/**
ADB command for rebooting the devices
 */
fun DeviceModel.adbReboot() {
    ExecUtil.execAndGetOutput(GeneralCommandLine("adb", "-s", device.toString(), "reboot"))
}

/**
ADB command for rebooting into fastboot
 */
fun DeviceModel.adbFastbootReboot() {
    ExecUtil.execAndGetOutput(GeneralCommandLine("adb", "-s", device.toString(), "reboot", "fastboot"))
}

/**
ADB command for rebooting into recovery
 */
fun DeviceModel.adbRecoveryReboot() {
    ExecUtil.execAndGetOutput(GeneralCommandLine("adb", "-s", device.toString(), "reboot", "recovery"))
}

/**
ADB command for rebooting into EDL mode
 */
fun DeviceModel.adbEdlReboot() {
    ExecUtil.execAndGetOutput(GeneralCommandLine("adb", "-s", device.toString(), "reboot", "edl"))
}

/**
Converts Array<IDevice> to MutableList<DeviceModel>
 */
fun Array<IDevice>.convert(): MutableList<DeviceModel> {
    return this.map { it.getModel() } as MutableList<DeviceModel>
}