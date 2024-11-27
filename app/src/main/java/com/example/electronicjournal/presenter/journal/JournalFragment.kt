package com.example.electronicjournal.presenter.journal

import android.content.Context

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.electronicjournal.R
import com.example.electronicjournal.databinding.FragmentJournalBinding
import com.example.electronicjournal.di.ViewModelFactory
import com.example.electronicjournal.di.appComponent
import com.example.electronicjournal.presenter.currentLesson.CurrentLessonFragment
import com.example.electronicjournal.presenter.journal.journalElements.JournalLesson
import javax.inject.Inject

class JournalFragment: Fragment(R.layout.fragment_journal), JournalAdapter.Listener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding: FragmentJournalBinding by viewBinding()

    private val viewModel: JournalViewModel by viewModels() {viewModelFactory}

    private val journalAdapter = JournalAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val groupId = arguments?.getInt("groupId")
        if (groupId != null) {
            viewModel.getTimetable(groupId)
        }
        with(binding.rvBase){
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = journalAdapter
        }
        viewModel.uiState.observe(viewLifecycleOwner){
            journalAdapter.submitList(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }


    override fun onClick(lesson: JournalLesson) {
        val bundle = Bundle()
        bundle.putInt("idLesson", lesson.id)
        val groupId = arguments?.getInt("groupId")
        arguments?.getInt("groupId")?.let { bundle.putInt("groupId", it) }
        bundle.putString("nameLesson", lesson.nameLesson)
        bundle.putString("fioTeacher", lesson.fioTeacher)
        bundle.putString("dateTime", lesson.date + " " + lesson.timeStart)
        Toast.makeText(requireContext(), lesson.nameLesson, Toast.LENGTH_LONG).show()
        val fr = CurrentLessonFragment()
        fr.arguments = bundle
        getParentFragmentManager()
            .beginTransaction()
            .replace(R.id.fl_main, fr)
            .addToBackStack(null)
            .commit()

    }

}
