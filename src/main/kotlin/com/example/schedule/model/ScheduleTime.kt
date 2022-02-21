package com.example.schedule.model

import java.time.LocalDateTime

class ScheduleTime(private val quantity: Int, private val type: ScheduleTimeType) {
    fun getTime(): Int {
        return quantity * type.quantity
    }
}

enum class ScheduleTimeType(val quantity: Int) {
    DAY(1),
    WEEK(7),
    MONTH(30),
    YEAR(365),
}