package com.mohith.model

import com.android.ddmlib.IDevice

data class DeviceModel(val id: String, var selected: Boolean, val device: IDevice? = null) {
    override fun toString(): String {
        return device.toString()
    }

    fun getName(): String {
        return device?.name?:"UNKNOWN"
    }
}

