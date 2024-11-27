package com.example.electronicjournal.presenter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.electronicjournal.R
import com.example.electronicjournal.databinding.FragmentRootBinding
import com.example.electronicjournal.di.ViewModelFactory
import com.example.electronicjournal.di.appComponent
import com.example.electronicjournal.presenter.journal.JournalFragment
import javax.inject.Inject

class RootFragment: Fragment(R.layout.fragment_root) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding: FragmentRootBinding by viewBinding()

    //private val viewModel: JournalViewModel by viewModels() {viewModelFactory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        openJournal()
        binding.bnvNav.setOnItemSelectedListener {
                item ->
            when(item.itemId){
                R.id.it_today -> {
                    openJournal()
                }
                else -> {

                }
            }
            true
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun openJournal(){
        val bundle = Bundle()
        arguments?.getInt("groupId")?.let { bundle.putInt("groupId", it) }
        val jf = JournalFragment()
        jf.arguments = bundle
        childFragmentManager
            .beginTransaction()
            .replace(binding.flMain.id, jf)
            .commit()
    }
}