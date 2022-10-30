package com.mohith.utils

import com.mohith.model.DeviceModel

interface DialogActionListener {
    fun onOk(deviceList: MutableList<DeviceModel>)
    fun onCancel()
}