package com.example.mystrava.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mystrava.R
import com.example.mystrava.databinding.FragmentOnboardingTooBinding
import com.example.mystrava.plugins.ViewBindingFragment
import com.example.mystrava.ui.SplashFragment

class OnboardingToo : ViewBindingFragment<FragmentOnboardingTooBinding>(FragmentOnboardingTooBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exitButton.setOnClickListener {
            onBoardingFinish()
            findNavController().navigate(R.id.action_onboardingViewerFragment_to_loginFragment)
        }
    }

    private fun onBoardingFinish() {
        val sharedPreferences = requireActivity().getSharedPreferences(SplashFragment.onBoarding, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(SplashFragment.finish,true)
        editor.apply()
    }
}
