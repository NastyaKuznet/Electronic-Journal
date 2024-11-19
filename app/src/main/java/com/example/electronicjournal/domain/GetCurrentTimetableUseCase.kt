package com.example.electronicjournal.domain

import com.example.electronicjournal.data.module.Lesson
import com.example.electronicjournal.data.network.repositories.RemoteDatabaseRepository
import java.util.Calendar
import javax.inject.Inject

interface GetCurrentTimetableUseCase {

    suspend operator fun invoke(groupId: Int): MutableMap<String, MutableList<Lesson>>
}

open class GetCurrentTimetableUseCaseImpl @Inject constructor(
    private val remoteDatabaseRepository: RemoteDatabaseRepository
): GetCurrentTimetableUseCase {

    override suspend fun invoke(groupId: Int): MutableMap<String, MutableList<Lesson>> {
        val list = remoteDatabaseRepository.getTimeTable(groupId)
        val numberWeek = (Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) + 1) % 2
        val map: MutableMap<String, MutableList<Lesson>> = mutableMapOf()
        for(el in list){
            if(el.number_week % 2 == numberWeek){
                if(el.week_day in map){
                    map[el.week_day]?.add(el)
                } else {
                    map[el.week_day] = mutableListOf()
                    map[el.week_day]?.add(el)
                }
            }
        }
        return map
    }
}