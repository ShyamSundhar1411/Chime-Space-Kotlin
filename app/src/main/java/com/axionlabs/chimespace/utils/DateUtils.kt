package com.axionlabs.chimespace.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun formatTimeStamp(isoTime: String): String{
    return try{
        val zonedTime = ZonedDateTime.parse(isoTime)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm a")
        zonedTime.format(formatter)
    } catch(e: Exception){
        isoTime
    }
}
