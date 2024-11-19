package com.example.electronicjournal.presenter.journal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.electronicjournal.R
import com.example.electronicjournal.databinding.FragmentJournalBinding
import com.example.electronicjournal.di.ViewModelFactory
import com.example.electronicjournal.di.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class JournalFragment: Fragment(R.layout.fragment_journal) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding: FragmentJournalBinding by viewBinding()

    private val viewModel: JournalViewModel by viewModels() {viewModelFactory}

    private val journalAdapter = JournalAdapter()

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
}
