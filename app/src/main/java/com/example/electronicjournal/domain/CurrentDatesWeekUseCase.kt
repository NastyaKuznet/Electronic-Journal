package com.example.electronicjournal.domain

import java.util.Calendar
import javax.inject.Inject

interface CurrentDatesWeekUseCase {

    suspend operator fun invoke():Map<Int,Pair<Int, Int>>
}

class CurrentDatesWeekUseCaseImpl @Inject constructor(

): CurrentDatesWeekUseCase {
    override suspend fun invoke(): Map<Int,Pair<Int, Int>> {
        val calendar = Calendar.getInstance()
        val today = calendar.get(Calendar.DAY_OF_WEEK)
        calendar.firstDayOfWeek = Calendar.MONDAY // Устанавливаем начало недели на понедельник
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        val daysOfWeek = mutableMapOf<Int,Pair<Int, Int>>()
        for (i in 0 until 7) {
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH) + 1 // Месяц +1, т.к. нумерация с 0
            daysOfWeek[dayOfWeek] = Pair(day, month)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return daysOfWeek
    }


}