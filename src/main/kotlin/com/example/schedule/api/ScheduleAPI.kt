package com.example.schedule.api

import com.example.schedule.model.Schedule
import com.example.schedule.model.ScheduleTime
import com.example.schedule.model.ScheduleTimeType
import java.time.LocalDateTime


class ScheduleAPI {
    val defaultList = mutableListOf(
        Schedule(
            id = 1,
            label = "vistoria de automóvel",
            description = "evento anual para regularização do automóvel",
            times = ScheduleTime(1, ScheduleTimeType.YEAR),
            date = LocalDateTime.now()
        ),
        Schedule(
            id = 2,
            label = "IPVA",
            times = ScheduleTime(1, ScheduleTimeType.YEAR),
            date = LocalDateTime.now()
        ),
        Schedule(
            id = 3,
            label = "consulta dentista",
            description = "tratamento dentário para remoção de placas e outros",
            times = ScheduleTime(6, ScheduleTimeType.MONTH),
            date = LocalDateTime.now()
        ),
    )
    val myList: MutableList<Schedule> = mutableListOf()

    fun add(schedule: Schedule) {
        this.myList.add(schedule)
    }

}