package com.example.schedule.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Schedule(
    val id: Long,
    val label: String,
    val description: String? = null,
    val times: ScheduleTime,
    val date: LocalDateTime
)
