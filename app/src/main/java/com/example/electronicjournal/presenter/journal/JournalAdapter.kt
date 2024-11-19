package com.example.electronicjournal.presenter.journal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicjournal.presenter.journal.journalElements.JournalDay
import com.example.electronicjournal.presenter.journal.journalElements.JournalItem
import com.example.electronicjournal.presenter.journal.journalElements.JournalLesson
import com.example.electronicjournal.presenter.journal.journalElements.JournalViewType
import com.example.electronicjournal.databinding.ItemDayBinding
import com.example.electronicjournal.databinding.ItemLessonBinding

class JournalAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listItems = mutableListOf<JournalItem>()

    fun submitList(list: List<JournalItem>){
        listItems = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(listItems[position]){
            is JournalLesson -> JournalViewType.LESSON
            is JournalDay -> JournalViewType.DAY
            else -> error("Unknown type of item in the list.")
        }.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType){
            JournalViewType.LESSON.ordinal -> LessonViewHolder(
                ItemLessonBinding.inflate(
                    inflater,
                    parent,
                    false)
            )
            JournalViewType.DAY.ordinal -> DayViewHolder(
                ItemDayBinding.inflate(
                    inflater,
                    parent,
                    false)
            )
            else -> error("Unknown type of item in the list.")
        }
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is LessonViewHolder -> holder.bind(listItems[position] as JournalLesson)
            is DayViewHolder -> holder.bind(listItems[position] as JournalDay)
        }
    }

    class LessonViewHolder(
        private val binding: ItemLessonBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: JournalLesson) {
            with(binding) {
                lesson.run {
                    tvTimeStart.text = timeStart
                    tvTimeEnd.text = timeEnd
                    tvTypeLesson.text = nameLesson
                    tvTypeLess.text = typeLesson
                    tvRoomLesson.text = room

                }
            }
        }
    }

    class DayViewHolder(
        private val binding: ItemDayBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(day: JournalDay){
            with(binding){
                day.run {
                    tvDay.text = nameDay
                }
            }
        }
    }
}