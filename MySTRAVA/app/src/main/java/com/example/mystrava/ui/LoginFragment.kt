package com.example.mystrava.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mystrava.R
import com.example.mystrava.databinding.FragmentLoginBinding
import com.example.mystrava.plugins.ViewBindingFragment
import com.example.mystrava.ui.loginvm.AuthViewModel
import com.google.android.material.snackbar.Snackbar
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse

class LoginFragment : ViewBindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
        binding.LoginButton.setOnClickListener {
            bindViewModel()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTH_REQUEST_CODE && data != null) {
            val tokenExchangeRequest = AuthorizationResponse.fromIntent(data)
                ?.createTokenExchangeRequest()
            val exception = AuthorizationException.fromIntent(data)
            when {
                tokenExchangeRequest != null && exception == null ->
                    viewModel.onAuthCodeReceived(tokenExchangeRequest)
                exception != null -> viewModel.onAuthCodeFailed()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun bindViewModel() {
        viewModel.openLoginPage()
        viewModel.loadingLiveData.observe(viewLifecycleOwner, ::updateIsLoading)
        viewModel.openAuthPageLiveData.observe(viewLifecycleOwner, ::openAuthPage)
        viewModel.toastLiveData.observe(viewLifecycleOwner, ::showMassage)
        viewModel.authSuccessLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    private fun showMassage(text: Int) {
        binding.LoginButton.isVisible = true
        Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun updateIsLoading(isLoading: Boolean) = with(binding) {
        loginProgress.isVisible = isLoading
        LoginButton.isVisible = isLoading.not()
    }

    private fun openAuthPage(intent: Intent) {
        startActivityForResult(intent, AUTH_REQUEST_CODE)
    }

    companion object {
        private const val AUTH_REQUEST_CODE = 342
    }
}
