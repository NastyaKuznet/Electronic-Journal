package com.example.electronicjournal.presenter.authorization

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.electronicjournal.R
import com.example.electronicjournal.databinding.FragmentAuthorizationBinding
import com.example.electronicjournal.di.ViewModelFactory
import com.example.electronicjournal.di.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.navigation.fragment.findNavController
import com.example.electronicjournal.presenter.models.ResultUIState

class AuthorizationFragment: Fragment(R.layout.fragment_authorization) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding: FragmentAuthorizationBinding by viewBinding()

    private val viewModel: AuthorizationViewModel by viewModels() {viewModelFactory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            bInput.setOnClickListener {
                viewModel.registration(
                    etLogin.text.toString(),
                    etPassword.text.toString())
                lifecycleScope.launch {
                    viewModel.uiState.collect { uiState ->
                        when (uiState.resultUIState) {
                            ResultUIState.Success -> {
                                Toast.makeText(requireContext(), "good", Toast.LENGTH_LONG).show()
                                val bundle = Bundle()
                                bundle.putInt("userId", uiState.user.id)
                                findNavController().navigate(
                                    R.id.rootFragment,
                                    bundle)
                            }
                            ResultUIState.Error -> {
                                Toast.makeText(requireContext(), "Такого пользователя нет", Toast.LENGTH_LONG).show()
                            }
                            else -> {}
                        }
                    }
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}