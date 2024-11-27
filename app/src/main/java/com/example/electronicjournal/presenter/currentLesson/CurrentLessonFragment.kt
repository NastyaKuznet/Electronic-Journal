package com.example.electronicjournal.presenter.currentLesson

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Spinner
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.electronicjournal.R
import com.example.electronicjournal.databinding.FragmentCurrentLessonBinding
import com.example.electronicjournal.di.ViewModelFactory
import com.example.electronicjournal.di.appComponent
import javax.inject.Inject

class CurrentLessonFragment: Fragment(R.layout.fragment_current_lesson) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding: FragmentCurrentLessonBinding by viewBinding()

    private val viewModel: CurrentLessonViewModel by viewModels() {viewModelFactory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val idLesson = arguments?.getInt("idLesson")
        val idGroup = arguments?.getInt("groupId")
        val nameLesson = arguments?.getString("nameLesson")
        val fioTeacher =  arguments?.getString("fioTeacher")
        val dateTime =  arguments?.getString("dateTime")

        if (idGroup != null && idLesson != null && dateTime != null) {
            with(viewModel){
                startAttendance(idLesson, dateTime)
                getStudents(idGroup)
            }
        }

        viewModel.attendanceHas.observe(viewLifecycleOwner){
            if(it){
                viewModel.attendanceOld.observe(viewLifecycleOwner) {
                    var count = 0
                    for (el in it) {
                        count++
                        val tableRow = LayoutInflater.from(requireContext())
                            .inflate(R.layout.row_item, null) as TableRow
                        tableRow.findViewById<TextView>(R.id.tv_number).setText(count.toString())
                        tableRow.findViewById<TextView>(R.id.tv_name)
                            .setText(el.lastname + " " + el.name + " " + el.patronymic)
                        tableRow.findViewById<Spinner>(R.id.spinner_1).visibility = View.INVISIBLE
                        tableRow.findViewById<TextView>(R.id.tv_atten).visibility = View.VISIBLE
                        tableRow.findViewById<TextView>(R.id.tv_atten).setText(
                            when(el.id_condition_student){
                                3 ->  "+"
                                1 -> "Н"
                                else -> "УП"
                            })
                        binding.tableLayout.addView(tableRow)

                    }
                    binding.bSave.visibility = View.INVISIBLE
                }
            } else {
                viewModel.attendanceState.observe(viewLifecycleOwner) {
                    if (it != null) {
                        var count = 0
                        for (el in it) {
                            count++
                            val tableRow = LayoutInflater.from(requireContext())
                                .inflate(R.layout.row_item, null) as TableRow
                            tableRow.findViewById<TextView>(R.id.tv_number)
                                .setText(count.toString())
                            tableRow.findViewById<TextView>(R.id.tv_name)
                                .setText(el.student.lastname + " " + el.student.name + " " + el.student.patronymic)
                            binding.tableLayout.addView(tableRow)

                        }
                    }
                }
            }
        }


        with(binding){
            tvTitle.text = nameLesson
            etTeacher.text = fioTeacher
            etTime.text = dateTime
            bSave.setOnClickListener {
                extractDataFromTableLayout(tableLayout)
                viewModel.save()
                viewModel.saveState.observe(viewLifecycleOwner) {
                    if(it != null && it == true) {
                        Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_LONG).show()
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                }
            }
            ibBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    fun extractDataFromTableLayout(tableLayout: TableLayout){
        if(viewModel.attendanceState.value != null) {
            for (i in 1 until tableLayout.childCount) {
                val tableRow = tableLayout.getChildAt(i) as TableRow
                val state = tableRow.findViewById<Spinner>(R.id.spinner_1)
                viewModel.attendanceState.value!![i - 1].attention = state.selectedItem.toString()
            }
        }
    }

}